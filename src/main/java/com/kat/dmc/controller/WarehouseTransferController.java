package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.dto.*;
import com.kat.dmc.common.util.DateUtil;
import com.kat.dmc.common.util.SQLErrorUtil;
import com.kat.dmc.common.util.StringUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.MaterialService;
import com.kat.dmc.service.interfaces.SupplierService;
import com.kat.dmc.service.interfaces.WarehouseService;
import com.kat.dmc.service.interfaces.WarehouseTransferService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.NoResultException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("warehouseTransfer")
@ViewScoped
public class WarehouseTransferController implements Serializable {

    @Autowired
    WarehouseTransferService warehouseTransferService;

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    UtilRepo utilRepo;

    @Autowired
    SupplierService supplierService;

    @Autowired
    MaterialService materialService;

    @Autowired
    SessionController sessionController;

    private ControllerAction.State currentAct;
    private boolean disableAdd;
    private boolean disableCopy;
    private boolean disableEdit;
    private boolean disableDelete;
    private MaterialTransferDto selectedWarehouseTransfer;
    private MaterialTransferDto tempWarehouseTransfer;
    private List<MaterialTransferDto> lstAllWarehouseTransfer;
    private List<MaterialTransferDto> lstFilteredWarehouseTransfer;
    private List<SupplierDto> lstAllSuppliers;
    private List<WarehouseDto> lstAllWarehouse;
    private List <MaterialDto> lstAllMaterial;


    @PostConstruct
    public void init(){
        setCurrentAct(ControllerAction.State.VIEW);
        lstAllWarehouseTransfer = warehouseTransferService.findAll();
        lstAllSuppliers = supplierService.findAllActive();
        lstAllWarehouse = warehouseService.findAllActiveByPermission(true, null, null, null);
        lstAllMaterial = materialService.findAllActive();
    }

    public void handleExcelTransferUpload(FileUploadEvent event){
        UploadedFile file = event.getFile();
//        String fileName = file.getFileName();
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputstream());
            // Getting the Sheet at index zero
            Sheet sheet = workbook.getSheetAt(0);
            // Create a DataFormatter to format and get each cell's value as String
            DataFormatter dataFormatter = new DataFormatter();
            int cols;
            int rows = 0;
            for (Row row : sheet) {
                cols = 0;
                rows++;
                String code = null;
                String quantity = null;
                String price = null;
                for (Cell cell : row) {
                    cols++;
                    String cellValue = dataFormatter.formatCellValue(cell);
//                    if(rows > 15 && cols == 2){
//
//                    }
                    if(rows > 15 && cols == sessionController.CODE_COL_NUM){
                        code = cellValue;
                    }
                    if(rows > 15 && cols == sessionController.QUANTITY_COL_NUM){
                        quantity = cellValue;
                    }
                    if(rows > 15 && cols == sessionController.PRICE_COL_NUM){
                        price = cellValue;
                    }
                }
                if(!StringUtil.isEmpty(code)
                        && StringUtil.isCode(code)){
                    makeMaterialFromCellData(code, quantity, price);
                }
            }
            // Closing the workbook
            workbook.close();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO
                    , "Transfer file thành công", "Đã tải file excel lên"));
            PrimeFaces.current().ajax().update("main:tblDetail");
        } catch (IOException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR
                    , "Không tìm thấy file", "Có lỗi xảy ra"));
        } catch (InvalidFormatException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR
                    , "File không đúng định dạng excel", "Có lỗi xảy ra"));
        }
    }

    private void makeMaterialFromCellData(String code, String quantity, String price){
        try {
            MaterialDto materialDto = materialService.findByCode(code.trim());
            MaterialTransferDetailDto materialTransferDto = new MaterialTransferDetailDto();
            materialTransferDto.setId(utilRepo.findSequenceNextval("dmc_material_transfer_detail_id_seq"));
            materialTransferDto.setCode("CT" + String.format("%06d", materialTransferDto.getId()));
            materialTransferDto.setTransferDate(selectedWarehouseTransfer.getTransferDate());
            materialTransferDto.setMaterialId(materialDto.getId());
            materialTransferDto.setMaterialGroupId(Integer.parseInt(materialDto.getMaterialGroupCode()));
            if (!StringUtil.isEmpty(quantity)
                    && StringUtils.isNumeric(quantity)) {
                materialTransferDto.setQuantity(Integer.parseInt(quantity));
            }
            price = StringUtil.removeDecimalPlace(price);
            if (!StringUtil.isEmpty(price)
                    && StringUtils.isNumeric(price)) {
                materialTransferDto.setPrice(Integer.parseInt(price));
            }
            quantity = StringUtil.removeDecimalPlace(quantity);
            if (!StringUtil.isEmpty(quantity)
                    && StringUtils.isNumeric(quantity)
                    && !StringUtil.isEmpty(price)
                    && StringUtils.isNumeric(price)) {
                materialTransferDto.setTotal(materialTransferDto.getPrice().longValue() * materialTransferDto.getQuantity());
            }
            if(selectedWarehouseTransfer.getLstDetails() == null){
                selectedWarehouseTransfer.setLstDetails(new ArrayList<>());
            }
            selectedWarehouseTransfer.getLstDetails().add(materialTransferDto);
        }catch (NoResultException ex){
        }
    }

    public void updatePrice(int idx){
        MaterialTransferDetailDto materialTransferDetailDto = selectedWarehouseTransfer.getLstDetails().get(idx);
        if(materialTransferDetailDto.getPrice() != null && materialTransferDetailDto.getQuantity() != null) {
            materialTransferDetailDto.setTotal(materialTransferDetailDto.getPrice().longValue() * materialTransferDetailDto.getQuantity());
        }
        Long allTotal = 0l;
        for(MaterialTransferDetailDto materialDto : selectedWarehouseTransfer.getLstDetails()){
            allTotal += materialDto.getTotal();
        }
        selectedWarehouseTransfer.setTotal(allTotal);
    }

    public void updateMaterial(int idx){
        MaterialTransferDetailDto materialTransferDetailDto = selectedWarehouseTransfer.getLstDetails().get(idx);
        for(MaterialDto materialDto : lstAllMaterial){
            if(materialDto.getId() == materialTransferDetailDto.getMaterialId()){
                materialTransferDetailDto.setMaterialGroupId(Integer.parseInt(materialDto.getMaterialGroupCode()));
                materialTransferDetailDto.setCode("CT" + String.format("%06d", materialTransferDetailDto.getId())+ "-" + materialDto.getCode());
                return;
            }
        }
    }

    public WarehouseDto getWarehouseById(Integer id){
        if(id == null){
            return new WarehouseDto();
        }
        for(WarehouseDto warehouseDto : lstAllWarehouse){
            if(warehouseDto.getId() == id){
                return warehouseDto;
            }
        }
        return new WarehouseDto();
    }

    public SupplierDto getSupplierById(Integer id){
        if(id == null){
            return new SupplierDto();
        }
        for(SupplierDto supplierDto : lstAllSuppliers){
            if(supplierDto.getId() == id){
                return supplierDto;
            }
        }
        return new SupplierDto();
    }

    public MaterialDto getMaterialById(Integer id){
        if(id == null){
            return new MaterialDto();
        }
        for(MaterialDto materialDto : lstAllMaterial){
            if(materialDto.getId() == id){
                return materialDto;
            }
        }
        return new MaterialDto();
    }

    public void actAddMaterial(){
        MaterialTransferDetailDto transferDto = new MaterialTransferDetailDto();
        transferDto.setTransferDate(selectedWarehouseTransfer.getTransferDate());
        transferDto.setId(utilRepo.findSequenceNextval("dmc_material_transfer_detail_id_seq"));
        transferDto.setCode("CT" + String.format("%06d", transferDto.getId()));
        if(selectedWarehouseTransfer.getLstDetails() == null){
            selectedWarehouseTransfer.setLstDetails(new ArrayList<>());
        }
        selectedWarehouseTransfer.getLstDetails().add(transferDto);
    }

    public void selectWarehouseTransfer(SelectEvent selectEvent){
        tempWarehouseTransfer = (MaterialTransferDto) selectEvent.getObject();
        selectedWarehouseTransfer = tempWarehouseTransfer.clone();
        selectedWarehouseTransfer.setLstDetails(warehouseTransferService.findAllActiveDtlByMaterialImpId(selectedWarehouseTransfer.getId()));
    }

    public void actAdd(){
        setCurrentAct(ControllerAction.State.ADD);
        selectedWarehouseTransfer = new MaterialTransferDto();
        selectedWarehouseTransfer.setTransferDate(DateUtil.getCurrentDayTS());
        selectedWarehouseTransfer.setId(utilRepo.findSequenceNextval("dmc_material_transfer_id_seq"));
        selectedWarehouseTransfer.setCode("CK" + String.format("%06d", selectedWarehouseTransfer.getId()));
        selectedWarehouseTransfer.setTransferFrom(0);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actCopy(){
        setCurrentAct(ControllerAction.State.COPY);
        selectedWarehouseTransfer = selectedWarehouseTransfer.clone();
        selectedWarehouseTransfer.setId(utilRepo.findSequenceNextval("dmc_material_transfer_id_seq"));
        selectedWarehouseTransfer.setCode("CK" + String.format("%06d", selectedWarehouseTransfer.getId()));
        selectedWarehouseTransfer.setTransferFrom(0);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actEdit(){
        setCurrentAct(ControllerAction.State.EDIT);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actDelete(){
        try{
            warehouseTransferService.delete(selectedWarehouseTransfer.getId());
            lstAllWarehouseTransfer.removeIf(s -> s.getId() == selectedWarehouseTransfer.getId());
            selectedWarehouseTransfer = null;
            setCurrentAct(ControllerAction.State.VIEW);
            PrimeFaces.current().executeScript("PF('blkList').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO
                    , "Info", "Xoá thành công"));
        }catch (Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR
                    , SQLErrorUtil.getSQLError(ex), "Có lỗi xảy ra"));
        }
    }
    public void actBack(){
        selectedWarehouseTransfer = tempWarehouseTransfer;
        setCurrentAct(ControllerAction.State.VIEW);
        PrimeFaces.current().executeScript("PF('blkList').hide()");
    }
    public void actAccept(){
        try {
            warehouseTransferService.save(selectedWarehouseTransfer);
            tempWarehouseTransfer = selectedWarehouseTransfer;
            if (getCurrentAct() == ControllerAction.State.ADD || getCurrentAct() == ControllerAction.State.COPY) {
                lstAllWarehouseTransfer.add(selectedWarehouseTransfer);
            }
            int slIdx = -1;
            for (int i = 0; i < lstAllWarehouseTransfer.size(); i++) {
                MaterialTransferDto warehouseTransferDto = lstAllWarehouseTransfer.get(i);
                if (warehouseTransferDto.getId() == selectedWarehouseTransfer.getId()) {
                    slIdx = i;
                }
            }
            if(slIdx != -1){
                lstAllWarehouseTransfer.set(slIdx, selectedWarehouseTransfer);
            }
            setCurrentAct(ControllerAction.State.VIEW);
            PrimeFaces.current().executeScript("PF('blkList').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO
                    , "", "Lưu thông tin thành công"));
        }catch (Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR
                    , SQLErrorUtil.getSQLError(ex), "Có lỗi xảy ra"));
        }
    }

    public ControllerAction.State getCurrentAct() {
        return currentAct;
    }

    public void setCurrentAct(ControllerAction.State currentAct) {
        this.currentAct = currentAct;
    }

    public boolean isDisableAdd() {
        return disableAdd;
    }

    public void setDisableAdd(boolean disableAdd) {
        this.disableAdd = disableAdd;
    }

    public boolean isDisableCopy() {
        return disableCopy;
    }

    public void setDisableCopy(boolean disableCopy) {
        this.disableCopy = disableCopy;
    }

    public boolean isDisableEdit() {
        return disableEdit;
    }

    public void setDisableEdit(boolean disableEdit) {
        this.disableEdit = disableEdit;
    }

    public boolean isDisableDelete() {
        return disableDelete;
    }

    public void setDisableDelete(boolean disableDelete) {
        this.disableDelete = disableDelete;
    }

    public MaterialTransferDto getSelectedWarehouseTransfer() {
        return selectedWarehouseTransfer;
    }

    public void setSelectedWarehouseTransfer(MaterialTransferDto selectedWarehouseTransfer) {
        this.selectedWarehouseTransfer = selectedWarehouseTransfer;
    }

    public List<MaterialTransferDto> getLstAllWarehouseTransfer() {
        return lstAllWarehouseTransfer;
    }

    public void setLstAllWarehouseTransfer(List<MaterialTransferDto> lstAllWarehouseTransfer) {
        this.lstAllWarehouseTransfer = lstAllWarehouseTransfer;
    }

    public List<MaterialTransferDto> getLstFilteredWarehouseTransfer() {
        return lstFilteredWarehouseTransfer;
    }

    public void setLstFilteredWarehouseTransfer(List<MaterialTransferDto> lstFilteredWarehouseTransfer) {
        this.lstFilteredWarehouseTransfer = lstFilteredWarehouseTransfer;
    }

    public List<SupplierDto> getLstAllSuppliers() {
        return lstAllSuppliers;
    }

    public void setLstAllSuppliers(List<SupplierDto> lstAllSuppliers) {
        this.lstAllSuppliers = lstAllSuppliers;
    }

    public List<WarehouseDto> getLstAllWarehouse() {
        return lstAllWarehouse;
    }

    public void setLstAllWarehouse(List<WarehouseDto> lstAllWarehouse) {
        this.lstAllWarehouse = lstAllWarehouse;
    }

    public List<MaterialDto> getLstAllMaterial() {
        return lstAllMaterial;
    }

    public void setLstAllMaterial(List<MaterialDto> lstAllMaterial) {
        this.lstAllMaterial = lstAllMaterial;
    }
}
