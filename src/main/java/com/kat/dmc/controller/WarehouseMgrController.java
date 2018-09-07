package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.dto.EmployeeDto;
import com.kat.dmc.common.dto.WarehouseDto;
import com.kat.dmc.common.util.SQLErrorUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.*;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("warehouseMgr")
@ViewScoped
public class WarehouseMgrController implements Serializable {

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    WarehouseImportService warehouseImportService;
    @Autowired
    WarehouseExportService warehouseExportService;
    @Autowired
    WarehouseTransferService warehouseTransferService;
    @Autowired
    WarehouseDismissService warehouseDismissService;

    @Autowired
    UtilRepo utilRepo;

    @Autowired
    EmployeeService employeeService;

    private ControllerAction.State currentAct;
    private boolean disableAdd;
    private boolean disableCopy;
    private boolean disableEdit;
    private boolean disableDelete;
    private WarehouseDto selectedWarehouse;
    private WarehouseDto tempWarehouse;
    private List<WarehouseDto> lstAllWarehouse;
    private List<WarehouseDto> lstFilteredWarehouse;
    private List<EmployeeDto> lstAllEmployees;


    @PostConstruct
    public void init(){
        setCurrentAct(ControllerAction.State.VIEW);
        lstAllWarehouse = warehouseService.findAll();
        lstAllEmployees = employeeService.findAllActive();
    }

    public void viewImportDetail(Integer idx){

        PrimeFaces.current().executeScript("PF('pnlDetailImport').show()");
    }
    public void viewExportDetail(Integer idx){

        PrimeFaces.current().executeScript("PF('pnlDetailExport').show()");
    }
    public void viewTransferDetail(Integer idx){

        PrimeFaces.current().executeScript("PF('pnlDetailTransfer').show()");
    }
    public void viewDismissDetail(Integer idx){

        PrimeFaces.current().executeScript("PF('pnlDetailDismiss').show()");
    }

    public void selectWarehouse(SelectEvent selectEvent){
        tempWarehouse = (WarehouseDto) selectEvent.getObject();
        selectedWarehouse = tempWarehouse.clone();
        selectedWarehouse.setLstImport(warehouseImportService.findAllActiveByWarehouseId(selectedWarehouse.getId()));
        selectedWarehouse.setLstExport(warehouseExportService.findAllActiveByWarehouseId(selectedWarehouse.getId()));
        selectedWarehouse.setLstTransfer(warehouseTransferService.findAllActiveByWarehouseId(selectedWarehouse.getId()));
        selectedWarehouse.setLstDismiss(warehouseDismissService.findAllActiveByWarehouseId(selectedWarehouse.getId()));
    }

    public void actAdd(){
        setCurrentAct(ControllerAction.State.ADD);
        selectedWarehouse = new WarehouseDto();
        selectedWarehouse.setId(utilRepo.findSequenceNextval("dmc_warehousr_id_seq"));
        selectedWarehouse.setCode("KO" + String.format("%06d", selectedWarehouse.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actCopy(){
        setCurrentAct(ControllerAction.State.COPY);
        selectedWarehouse = selectedWarehouse.clone();
        selectedWarehouse.setId(utilRepo.findSequenceNextval("dmc_warehousr_id_seq"));
        selectedWarehouse.setCode("KO" + String.format("%06d", selectedWarehouse.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actEdit(){
        setCurrentAct(ControllerAction.State.EDIT);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actDelete(){
        try{
            warehouseService.delete(selectedWarehouse.getId());
            lstAllWarehouse.removeIf(s -> s.getId() == selectedWarehouse.getId());
            selectedWarehouse = null;
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
        selectedWarehouse = tempWarehouse;
        setCurrentAct(ControllerAction.State.VIEW);
        PrimeFaces.current().executeScript("PF('blkList').hide()");
    }
    public void actAccept(){
        try {
            warehouseService.save(selectedWarehouse);
            tempWarehouse = selectedWarehouse;
            if (getCurrentAct() == ControllerAction.State.ADD || getCurrentAct() == ControllerAction.State.COPY) {
                lstAllWarehouse.add(selectedWarehouse);
            }
            int slIdx = -1;
            for (int i = 0; i < lstAllWarehouse.size(); i++) {
                WarehouseDto warehouseDto = lstAllWarehouse.get(i);
                if (warehouseDto.getId() == selectedWarehouse.getId()) {
                    slIdx = i;
                }
            }
            if(slIdx != -1){
                lstAllWarehouse.set(slIdx, selectedWarehouse);
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

    public WarehouseDto getSelectedWarehouse() {
        return selectedWarehouse;
    }

    public void setSelectedWarehouse(WarehouseDto selectedWarehouse) {
        this.selectedWarehouse = selectedWarehouse;
    }

    public List<WarehouseDto> getLstAllWarehouse() {
        return lstAllWarehouse;
    }

    public void setLstAllWarehouse(List<WarehouseDto> lstAllWarehouse) {
        this.lstAllWarehouse = lstAllWarehouse;
    }

    public List<WarehouseDto> getLstFilteredWarehouse() {
        return lstFilteredWarehouse;
    }

    public void setLstFilteredWarehouse(List<WarehouseDto> lstFilteredWarehouse) {
        this.lstFilteredWarehouse = lstFilteredWarehouse;
    }

    public List<EmployeeDto> getLstAllEmployees() {
        return lstAllEmployees;
    }

    public void setLstAllEmployees(List<EmployeeDto> lstAllEmployees) {
        this.lstAllEmployees = lstAllEmployees;
    }
}
