package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.model.*;
import com.kat.dmc.common.util.DateUtil;
import com.kat.dmc.common.util.SQLErrorUtil;
import com.kat.dmc.common.util.StringUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.MaterialService;
import com.kat.dmc.service.interfaces.SupplierService;
import com.kat.dmc.service.interfaces.WarehouseExportService;
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
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.NoResultException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("warehouseExport")
@ConversationScoped
public class WarehouseExportController implements Serializable {

    @Autowired
    WarehouseExportService warehouseExportService;

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
    private MaterialExportDto selectedWarehouseExport;
    private MaterialExportDto tempWarehouseExport;
    private List<MaterialExportDto> lstAllWarehouseExport;
    private List<MaterialExportDto> lstFilteredWarehouseExport;
    private List<SupplierDto> lstAllSuppliers;
    private List<WarehouseDto> lstAllWarehouse;
    private List <MaterialDto> lstAllMaterial;


    @PostConstruct
    public void init(){
        setCurrentAct(ControllerAction.State.VIEW);
        lstAllWarehouseExport = warehouseExportService.findAll();
        lstAllSuppliers = supplierService.findAllActive();
        lstAllWarehouse = warehouseService.findAllActiveByPermission(true, null, null, null);
        lstAllMaterial = materialService.findAllActive();
    }

    public void handleExcelExportUpload(FileUploadEvent event){
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
                    , "Export file thành công", "Đã tải file excel lên"));
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
            MaterialExportDetailDto materialExportDto = new MaterialExportDetailDto();
            materialExportDto.setId(utilRepo.findSequenceNextval("dmc_material_export_detail_id_seq"));
            materialExportDto.setCode("XT" + String.format("%06d", materialExportDto.getId()));
            materialExportDto.setExportDate(selectedWarehouseExport.getExportDate());
            materialExportDto.setMaterialId(materialDto.getId());
            materialExportDto.setMaterialGroupId(Integer.parseInt(materialDto.getMaterialGroupCode()));
            if (!StringUtil.isEmpty(quantity)
                    && StringUtils.isNumeric(quantity)) {
                materialExportDto.setQuantity(Integer.parseInt(quantity));
            }
            price = StringUtil.removeDecimalPlace(price);
            if (!StringUtil.isEmpty(price)
                    && StringUtils.isNumeric(price)) {
                materialExportDto.setPrice(Integer.parseInt(price));
            }
            quantity = StringUtil.removeDecimalPlace(quantity);
            if (!StringUtil.isEmpty(quantity)
                    && StringUtils.isNumeric(quantity)
                    && !StringUtil.isEmpty(price)
                    && StringUtils.isNumeric(price)) {
                materialExportDto.setTotal(materialExportDto.getPrice().longValue() * materialExportDto.getQuantity());
            }
            if(selectedWarehouseExport.getLstDetails() == null){
                selectedWarehouseExport.setLstDetails(new ArrayList<>());
            }
            selectedWarehouseExport.getLstDetails().add(materialExportDto);
        }catch (NoResultException ex){
            ex.printStackTrace();
        }
    }

    public void updatePrice(int idx){
        MaterialExportDetailDto materialExportDetailDto = selectedWarehouseExport.getLstDetails().get(idx);
        if(materialExportDetailDto.getPrice() != null && materialExportDetailDto.getQuantity() != null) {
            materialExportDetailDto.setTotal(materialExportDetailDto.getPrice().longValue() * materialExportDetailDto.getQuantity());
        }
        Long allTotal = 0l;
        for(MaterialExportDetailDto materialDto : selectedWarehouseExport.getLstDetails()){
            allTotal += materialDto.getTotal();
        }
        selectedWarehouseExport.setTotal(allTotal);
    }

    public void updateMaterial(int idx){
        MaterialExportDetailDto materialExportDetailDto = selectedWarehouseExport.getLstDetails().get(idx);
        for(MaterialDto materialDto : lstAllMaterial){
            if(materialDto.getId() == materialExportDetailDto.getMaterialId()){
                materialExportDetailDto.setMaterialGroupId(Integer.parseInt(materialDto.getMaterialGroupCode()));
                materialExportDetailDto.setCode("XT" + String.format("%06d", materialExportDetailDto.getId())+ "-" + materialDto.getCode());
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
        MaterialExportDetailDto exportDto = new MaterialExportDetailDto();
        exportDto.setExportDate(selectedWarehouseExport.getExportDate());
        exportDto.setId(utilRepo.findSequenceNextval("dmc_material_export_detail_id_seq"));
        exportDto.setCode("XT" + String.format("%06d", exportDto.getId()));
        if(selectedWarehouseExport.getLstDetails() == null){
            selectedWarehouseExport.setLstDetails(new ArrayList<>());
        }
        selectedWarehouseExport.getLstDetails().add(exportDto);
    }

    public void selectWarehouseExport(SelectEvent selectEvent){
        tempWarehouseExport = (MaterialExportDto) selectEvent.getObject();
        selectedWarehouseExport = tempWarehouseExport.clone();
        selectedWarehouseExport.setLstDetails(warehouseExportService.findAllActiveDtlByMaterialImpId(selectedWarehouseExport.getId()));
    }

    public void actAdd(){
        setCurrentAct(ControllerAction.State.ADD);
        selectedWarehouseExport = new MaterialExportDto();
        selectedWarehouseExport.setExportDate(DateUtil.getCurrentDayTS());
        selectedWarehouseExport.setId(utilRepo.findSequenceNextval("dmc_material_export_id_seq"));
        selectedWarehouseExport.setCode("XK" + String.format("%06d", selectedWarehouseExport.getId()));
        selectedWarehouseExport.setExportFrom(0);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actCopy(){
        setCurrentAct(ControllerAction.State.COPY);
        selectedWarehouseExport = selectedWarehouseExport.clone();
        selectedWarehouseExport.setId(utilRepo.findSequenceNextval("dmc_material_export_id_seq"));
        selectedWarehouseExport.setCode("XK" + String.format("%06d", selectedWarehouseExport.getId()));
        selectedWarehouseExport.setExportFrom(0);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actEdit(){
        setCurrentAct(ControllerAction.State.EDIT);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actDelete(){
        try{
            warehouseExportService.delete(selectedWarehouseExport.getId());
            lstAllWarehouseExport.removeIf(s -> s.getId() == selectedWarehouseExport.getId());
            selectedWarehouseExport = null;
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
        selectedWarehouseExport = tempWarehouseExport;
        setCurrentAct(ControllerAction.State.VIEW);
        PrimeFaces.current().executeScript("PF('blkList').hide()");
    }
    public void actAccept(){
        try {
            warehouseExportService.save(selectedWarehouseExport);
            tempWarehouseExport = selectedWarehouseExport;
            if (getCurrentAct() == ControllerAction.State.ADD || getCurrentAct() == ControllerAction.State.COPY) {
                lstAllWarehouseExport.add(selectedWarehouseExport);
            }
            int slIdx = -1;
            for (int i = 0; i < lstAllWarehouseExport.size(); i++) {
                MaterialExportDto warehouseExportDto = lstAllWarehouseExport.get(i);
                if (warehouseExportDto.getId() == selectedWarehouseExport.getId()) {
                    slIdx = i;
                }
            }
            if(slIdx != -1){
                lstAllWarehouseExport.set(slIdx, selectedWarehouseExport);
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

    public MaterialExportDto getSelectedWarehouseExport() {
        return selectedWarehouseExport;
    }

    public void setSelectedWarehouseExport(MaterialExportDto selectedWarehouseExport) {
        this.selectedWarehouseExport = selectedWarehouseExport;
    }

    public List<MaterialExportDto> getLstAllWarehouseExport() {
        return lstAllWarehouseExport;
    }

    public void setLstAllWarehouseExport(List<MaterialExportDto> lstAllWarehouseExport) {
        this.lstAllWarehouseExport = lstAllWarehouseExport;
    }

    public List<MaterialExportDto> getLstFilteredWarehouseExport() {
        return lstFilteredWarehouseExport;
    }

    public void setLstFilteredWarehouseExport(List<MaterialExportDto> lstFilteredWarehouseExport) {
        this.lstFilteredWarehouseExport = lstFilteredWarehouseExport;
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
