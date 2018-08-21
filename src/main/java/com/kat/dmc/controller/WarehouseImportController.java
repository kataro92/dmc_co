package com.kat.dmc.controller;


import com.kat.dmc.common.constant.CommonConst;
import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.model.*;
import com.kat.dmc.common.util.DateUtil;
import com.kat.dmc.common.util.SQLErrorUtil;
import com.kat.dmc.common.util.StringUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Named("warehouseImport")
@ConversationScoped
public class WarehouseImportController implements Serializable {

    @Autowired
    WarehouseImportService warehouseImportService;

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

    @Autowired
    BuildingService buildingService;

    private ControllerAction.State currentAct;
    private boolean disableAdd;
    private boolean disableCopy;
    private boolean disableEdit;
    private boolean disableDelete;
    private MaterialImportDto selectedWarehouseImport;
    private MaterialImportDto tempWarehouseImport;
    private List<MaterialImportDto> lstAllWarehouseImport;
    private List<MaterialImportDto> lstFilteredWarehouseImport;
    private List<SupplierDto> lstAllSuppliers;
    private List<WarehouseDto> lstAllWarehouse;
    private List <MaterialDto> lstAllMaterial;


    @PostConstruct
    public void init(){
        setCurrentAct(ControllerAction.State.VIEW);
        lstAllWarehouseImport = warehouseImportService.findAll();
        lstAllSuppliers = supplierService.findAllActive();
        lstAllWarehouse = warehouseService.findAllActiveByPermission(true, null, null, null);
        lstAllMaterial = materialService.findAllActive();
    }

    public void handleExcelImportUpload(FileUploadEvent event){
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
                    , "Import file thành công", "Đã tải file excel lên"));
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
            MaterialImportDetailDto materialImportDto = new MaterialImportDetailDto();
            materialImportDto.setId(utilRepo.findSequenceNextval("dmc_material_import_detail_id_seq"));
            materialImportDto.setCode("NT" + String.format("%06d", materialImportDto.getId()));
            materialImportDto.setImportDate(selectedWarehouseImport.getImportDate());
            materialImportDto.setMaterialId(materialDto.getId());
            materialImportDto.setMaterialGroupId(Integer.parseInt(materialDto.getMaterialGroupCode()));
            if (!StringUtil.isEmpty(quantity)
                    && StringUtils.isNumeric(quantity)) {
                materialImportDto.setQuantity(Integer.parseInt(quantity));
            }
            price = StringUtil.removeDecimalPlace(price);
            if (!StringUtil.isEmpty(price)
                    && StringUtils.isNumeric(price)) {
                materialImportDto.setPrice(Integer.parseInt(price));
            }
            quantity = StringUtil.removeDecimalPlace(quantity);
            if (!StringUtil.isEmpty(quantity)
                    && StringUtils.isNumeric(quantity)
                    && !StringUtil.isEmpty(price)
                    && StringUtils.isNumeric(price)) {
                materialImportDto.setTotal(materialImportDto.getPrice().longValue() * materialImportDto.getQuantity());
            }
            if(selectedWarehouseImport.getLstDetails() == null){
                selectedWarehouseImport.setLstDetails(new ArrayList<>());
            }
            selectedWarehouseImport.getLstDetails().add(materialImportDto);
        }catch (NoResultException ex){
            ex.printStackTrace();
        }
    }

    public void updatePrice(int idx){
        MaterialImportDetailDto materialImportDetailDto = selectedWarehouseImport.getLstDetails().get(idx);
        if(materialImportDetailDto.getPrice() != null && materialImportDetailDto.getQuantity() != null) {
            materialImportDetailDto.setTotal(materialImportDetailDto.getPrice().longValue() * materialImportDetailDto.getQuantity());
        }
        Long allTotal = 0l;
        for(MaterialImportDetailDto materialDto : selectedWarehouseImport.getLstDetails()){
            allTotal += materialDto.getTotal();
        }
        selectedWarehouseImport.setTotal(allTotal);
    }

    public void updateMaterial(int idx){
        MaterialImportDetailDto materialImportDetailDto = selectedWarehouseImport.getLstDetails().get(idx);
        for(MaterialDto materialDto : lstAllMaterial){
            if(materialDto.getId() == materialImportDetailDto.getMaterialId()){
                materialImportDetailDto.setMaterialGroupId(Integer.parseInt(materialDto.getMaterialGroupCode()));
                materialImportDetailDto.setCode("NT" + String.format("%06d", materialImportDetailDto.getId())+ "-" + materialDto.getCode());
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
        MaterialImportDetailDto importDto = new MaterialImportDetailDto();
        importDto.setImportDate(selectedWarehouseImport.getImportDate());
        importDto.setId(utilRepo.findSequenceNextval("dmc_material_import_detail_id_seq"));
        importDto.setCode("NT" + String.format("%06d", importDto.getId()));
        if(selectedWarehouseImport.getLstDetails() == null){
            selectedWarehouseImport.setLstDetails(new ArrayList<>());
        }
        selectedWarehouseImport.getLstDetails().add(importDto);
    }

    public void selectWarehouseImport(SelectEvent selectEvent){
        tempWarehouseImport = (MaterialImportDto) selectEvent.getObject();
        selectedWarehouseImport = tempWarehouseImport.clone();
        if(String.valueOf(selectedWarehouseImport.getCategoryId()).equals(CommonConst.Code.IMPORT_CATEGORY_ID_0.code())) {
            //Vật tư
            selectedWarehouseImport.setLstDetails(warehouseImportService.findAllActiveDtlByMaterialImpId(selectedWarehouseImport.getId()));
        }else if(String.valueOf(selectedWarehouseImport.getCategoryId()).equals(CommonConst.Code.IMPORT_CATEGORY_ID_1.code())) {
            //Thành phẩm
            selectedWarehouseImport.setLstDetails(buildingService.findAllActiveDtlByMaterialImpId(selectedWarehouseImport.getId()));
        }
    }

    public void actAdd(){
        setCurrentAct(ControllerAction.State.ADD);
        selectedWarehouseImport = new MaterialImportDto();
        selectedWarehouseImport.setImportDate(DateUtil.getCurrentDayTS());
        selectedWarehouseImport.setId(utilRepo.findSequenceNextval("dmc_material_import_id_seq"));
        selectedWarehouseImport.setCode("NK" + String.format("%06d", selectedWarehouseImport.getId()));
        selectedWarehouseImport.setImportFrom(0);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actCopy(){
        if(selectedWarehouseImport.getImportFrom() != 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN
                    , "Warn", "Không được sao chép phiếu nhập sản phẩm "));
            return;
        }
        setCurrentAct(ControllerAction.State.COPY);
        selectedWarehouseImport = selectedWarehouseImport.clone();
        selectedWarehouseImport.setId(utilRepo.findSequenceNextval("dmc_material_import_id_seq"));
        selectedWarehouseImport.setCode("NK" + String.format("%06d", selectedWarehouseImport.getId()));
        selectedWarehouseImport.setImportFrom(0);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actEdit(){
        if(selectedWarehouseImport.getImportFrom() != 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN
                    , "Warn", "Không được sửa phiếu nhập sản phẩm "));
            return;
        }
        setCurrentAct(ControllerAction.State.EDIT);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actDelete(){
        try{
            if(selectedWarehouseImport.getImportFrom() != 0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN
                        , "Warn", "Không được xóa phiếu nhập sản phẩm "));
                return;
            }
            warehouseImportService.delete(selectedWarehouseImport.getId());
            lstAllWarehouseImport.removeIf(s -> s.getId() == selectedWarehouseImport.getId());
            selectedWarehouseImport = null;
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
        selectedWarehouseImport = tempWarehouseImport;
        setCurrentAct(ControllerAction.State.VIEW);
        PrimeFaces.current().executeScript("PF('blkList').hide()");
    }
    public void actAccept(){
        try {
            warehouseImportService.save(selectedWarehouseImport);
            tempWarehouseImport = selectedWarehouseImport;
            if (getCurrentAct() == ControllerAction.State.ADD || getCurrentAct() == ControllerAction.State.COPY) {
                lstAllWarehouseImport.add(selectedWarehouseImport);
            }
            int slIdx = -1;
            for (int i = 0; i < lstAllWarehouseImport.size(); i++) {
                MaterialImportDto warehouseImportDto = lstAllWarehouseImport.get(i);
                if (warehouseImportDto.getId() == selectedWarehouseImport.getId()) {
                    slIdx = i;
                }
            }
            if(slIdx != -1){
                lstAllWarehouseImport.set(slIdx, selectedWarehouseImport);
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

    public MaterialImportDto getSelectedWarehouseImport() {
        return selectedWarehouseImport;
    }

    public void setSelectedWarehouseImport(MaterialImportDto selectedWarehouseImport) {
        this.selectedWarehouseImport = selectedWarehouseImport;
    }

    public List<MaterialImportDto> getLstAllWarehouseImport() {
        return lstAllWarehouseImport;
    }

    public void setLstAllWarehouseImport(List<MaterialImportDto> lstAllWarehouseImport) {
        this.lstAllWarehouseImport = lstAllWarehouseImport;
    }

    public List<MaterialImportDto> getLstFilteredWarehouseImport() {
        return lstFilteredWarehouseImport;
    }

    public void setLstFilteredWarehouseImport(List<MaterialImportDto> lstFilteredWarehouseImport) {
        this.lstFilteredWarehouseImport = lstFilteredWarehouseImport;
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
