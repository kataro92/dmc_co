package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.dto.*;
import com.kat.dmc.common.util.DateUtil;
import com.kat.dmc.common.util.SQLErrorUtil;
import com.kat.dmc.common.util.StringUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.MaterialService;
import com.kat.dmc.service.interfaces.SupplierService;
import com.kat.dmc.service.interfaces.WarehouseDismissService;
import com.kat.dmc.service.interfaces.WarehouseService;
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

@Named("warehouseDismiss")
@ViewScoped
public class WarehouseDismissController implements Serializable {

    @Autowired
    WarehouseDismissService warehouseDismissService;

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
    private MaterialDismissDto selectedWarehouseDismiss;
    private MaterialDismissDto tempWarehouseDismiss;
    private List<MaterialDismissDto> lstAllWarehouseDismiss;
    private List<MaterialDismissDto> lstFilteredWarehouseDismiss;
    private List<SupplierDto> lstAllSuppliers;
    private List<WarehouseDto> lstAllWarehouse;
    private List <MaterialDto> lstAllMaterial;


    @PostConstruct
    public void init(){
        setCurrentAct(ControllerAction.State.VIEW);
        lstAllWarehouseDismiss = warehouseDismissService.findAll();
        lstAllSuppliers = supplierService.findAllActive();
        lstAllWarehouse = warehouseService.findAllActiveByPermission(true, null, null, null);
        lstAllMaterial = materialService.findAllActive();
    }

    public void handleExcelDismissUpload(FileUploadEvent event){
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
                    , "Dismiss file thành công", "Đã tải file excel lên"));
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
            MaterialDismissDetailDto materialDismissDto = new MaterialDismissDetailDto();
            materialDismissDto.setId(utilRepo.findSequenceNextval("dmc_material_dismiss_detail_id_seq"));
            materialDismissDto.setCode("HT" + String.format("%06d", materialDismissDto.getId()));
            materialDismissDto.setDismissDate(selectedWarehouseDismiss.getDismissDate());
            materialDismissDto.setMaterialId(materialDto.getId());
            materialDismissDto.setMaterialGroupId(Integer.parseInt(materialDto.getMaterialGroupCode()));
            if (!StringUtil.isEmpty(quantity)
                    && StringUtils.isNumeric(quantity)) {
                materialDismissDto.setQuantity(Integer.parseInt(quantity));
            }
            price = StringUtil.removeDecimalPlace(price);
            if (!StringUtil.isEmpty(price)
                    && StringUtils.isNumeric(price)) {
                materialDismissDto.setPrice(Integer.parseInt(price));
            }
            quantity = StringUtil.removeDecimalPlace(quantity);
            if (!StringUtil.isEmpty(quantity)
                    && StringUtils.isNumeric(quantity)
                    && !StringUtil.isEmpty(price)
                    && StringUtils.isNumeric(price)) {
                materialDismissDto.setTotal(materialDismissDto.getPrice().longValue() * materialDismissDto.getQuantity());
            }
            if(selectedWarehouseDismiss.getLstDetails() == null){
                selectedWarehouseDismiss.setLstDetails(new ArrayList<>());
            }
            selectedWarehouseDismiss.getLstDetails().add(materialDismissDto);
        }catch (NoResultException ex){
        }
    }

    public void updatePrice(int idx){
        MaterialDismissDetailDto materialDismissDetailDto = selectedWarehouseDismiss.getLstDetails().get(idx);
        if(materialDismissDetailDto.getPrice() != null && materialDismissDetailDto.getQuantity() != null) {
            materialDismissDetailDto.setTotal(materialDismissDetailDto.getPrice().longValue() * materialDismissDetailDto.getQuantity());
        }
        Long allTotal = 0l;
        for(MaterialDismissDetailDto materialDto : selectedWarehouseDismiss.getLstDetails()){
            allTotal += materialDto.getTotal();
        }
        selectedWarehouseDismiss.setTotal(allTotal);
    }

    public void updateMaterial(int idx){
        MaterialDismissDetailDto materialDismissDetailDto = selectedWarehouseDismiss.getLstDetails().get(idx);
        for(MaterialDto materialDto : lstAllMaterial){
            if(materialDto.getId() == materialDismissDetailDto.getMaterialId()){
                materialDismissDetailDto.setMaterialGroupId(Integer.parseInt(materialDto.getMaterialGroupCode()));
                materialDismissDetailDto.setCode("HT" + String.format("%06d", materialDismissDetailDto.getId())+ "-" + materialDto.getCode());
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
        MaterialDismissDetailDto dismissDto = new MaterialDismissDetailDto();
        dismissDto.setDismissDate(selectedWarehouseDismiss.getDismissDate());
        dismissDto.setId(utilRepo.findSequenceNextval("dmc_material_dismiss_detail_id_seq"));
        dismissDto.setCode("HT" + String.format("%06d", dismissDto.getId()));
        if(selectedWarehouseDismiss.getLstDetails() == null){
            selectedWarehouseDismiss.setLstDetails(new ArrayList<>());
        }
        selectedWarehouseDismiss.getLstDetails().add(dismissDto);
    }

    public void selectWarehouseDismiss(SelectEvent selectEvent){
        tempWarehouseDismiss = (MaterialDismissDto) selectEvent.getObject();
        selectedWarehouseDismiss = tempWarehouseDismiss.clone();
        selectedWarehouseDismiss.setLstDetails(warehouseDismissService.findAllActiveDtlByMaterialImpId(selectedWarehouseDismiss.getId()));
    }

    public void actAdd(){
        setCurrentAct(ControllerAction.State.ADD);
        selectedWarehouseDismiss = new MaterialDismissDto();
        selectedWarehouseDismiss.setDismissDate(DateUtil.getCurrentDayTS());
        selectedWarehouseDismiss.setId(utilRepo.findSequenceNextval("dmc_material_dismiss_id_seq"));
        selectedWarehouseDismiss.setCode("HK" + String.format("%06d", selectedWarehouseDismiss.getId()));
        selectedWarehouseDismiss.setDismissFrom(0);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actCopy(){
        setCurrentAct(ControllerAction.State.COPY);
        selectedWarehouseDismiss = selectedWarehouseDismiss.clone();
        selectedWarehouseDismiss.setId(utilRepo.findSequenceNextval("dmc_material_dismiss_id_seq"));
        selectedWarehouseDismiss.setCode("HK" + String.format("%06d", selectedWarehouseDismiss.getId()));
        selectedWarehouseDismiss.setDismissFrom(0);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actEdit(){
        setCurrentAct(ControllerAction.State.EDIT);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actDelete(){
        try{
            warehouseDismissService.delete(selectedWarehouseDismiss.getId());
            lstAllWarehouseDismiss.removeIf(s -> s.getId() == selectedWarehouseDismiss.getId());
            selectedWarehouseDismiss = null;
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
        selectedWarehouseDismiss = tempWarehouseDismiss;
        setCurrentAct(ControllerAction.State.VIEW);
        PrimeFaces.current().executeScript("PF('blkList').hide()");
    }
    public void actAccept(){
        try {
            warehouseDismissService.save(selectedWarehouseDismiss);
            tempWarehouseDismiss = selectedWarehouseDismiss;
            if (getCurrentAct() == ControllerAction.State.ADD || getCurrentAct() == ControllerAction.State.COPY) {
                lstAllWarehouseDismiss.add(selectedWarehouseDismiss);
            }
            int slIdx = -1;
            for (int i = 0; i < lstAllWarehouseDismiss.size(); i++) {
                MaterialDismissDto warehouseDismissDto = lstAllWarehouseDismiss.get(i);
                if (warehouseDismissDto.getId() == selectedWarehouseDismiss.getId()) {
                    slIdx = i;
                }
            }
            if(slIdx != -1){
                lstAllWarehouseDismiss.set(slIdx, selectedWarehouseDismiss);
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

    public MaterialDismissDto getSelectedWarehouseDismiss() {
        return selectedWarehouseDismiss;
    }

    public void setSelectedWarehouseDismiss(MaterialDismissDto selectedWarehouseDismiss) {
        this.selectedWarehouseDismiss = selectedWarehouseDismiss;
    }

    public List<MaterialDismissDto> getLstAllWarehouseDismiss() {
        return lstAllWarehouseDismiss;
    }

    public void setLstAllWarehouseDismiss(List<MaterialDismissDto> lstAllWarehouseDismiss) {
        this.lstAllWarehouseDismiss = lstAllWarehouseDismiss;
    }

    public List<MaterialDismissDto> getLstFilteredWarehouseDismiss() {
        return lstFilteredWarehouseDismiss;
    }

    public void setLstFilteredWarehouseDismiss(List<MaterialDismissDto> lstFilteredWarehouseDismiss) {
        this.lstFilteredWarehouseDismiss = lstFilteredWarehouseDismiss;
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
