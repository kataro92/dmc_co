package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.model.EmployeeDto;
import com.kat.dmc.common.model.SupplierDto;
import com.kat.dmc.common.util.SQLErrorUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.EmployeeService;
import com.kat.dmc.service.interfaces.SupplierService;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("supplierMgr")
@ConversationScoped
public class SupplierMgrController implements Serializable {

    @Autowired
    SupplierService supplierService;

    @Autowired
    UtilRepo utilRepo;

    @Autowired
    EmployeeService employeeService;

    private ControllerAction.State currentAct;
    private boolean disableAdd;
    private boolean disableCopy;
    private boolean disableEdit;
    private boolean disableDelete;
    private SupplierDto selectedSupplier;
    private SupplierDto tempSupplier;
    private List<SupplierDto> lstAllSupplier;
    private List<SupplierDto> lstFilteredSupplier;
    private List<EmployeeDto> lstAllEmployees;


    @PostConstruct
    public void init(){
        setCurrentAct(ControllerAction.State.VIEW);
        lstAllSupplier = supplierService.findAll();
        lstAllEmployees = employeeService.findAllActive();
    }

    public void selectSupplier(SelectEvent selectEvent){
        tempSupplier = (SupplierDto) selectEvent.getObject();
        selectedSupplier = tempSupplier.clone();
    }

    public void actAdd(){
        setCurrentAct(ControllerAction.State.ADD);
        selectedSupplier = new SupplierDto();
        selectedSupplier.setId(utilRepo.findSequenceNextval("supplier__id_seq"));
        selectedSupplier.setCode("CL" + String.format("%06d", selectedSupplier.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actCopy(){
        setCurrentAct(ControllerAction.State.COPY);
        selectedSupplier = selectedSupplier.clone();
        selectedSupplier.setId(utilRepo.findSequenceNextval("supplier__id_seq"));
        selectedSupplier.setCode("CL" + String.format("%06d", selectedSupplier.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actEdit(){
        setCurrentAct(ControllerAction.State.EDIT);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actDelete(){
        try{
            supplierService.delete(selectedSupplier.getId());
            lstAllSupplier.removeIf(s -> s.getId() == selectedSupplier.getId());
            selectedSupplier = null;
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
        selectedSupplier = tempSupplier;
        setCurrentAct(ControllerAction.State.VIEW);
        PrimeFaces.current().executeScript("PF('blkList').hide()");
    }
    public void actAccept(){
        try {
            supplierService.save(selectedSupplier);
            tempSupplier = selectedSupplier;
            if (getCurrentAct() == ControllerAction.State.ADD || getCurrentAct() == ControllerAction.State.COPY) {
                lstAllSupplier.add(selectedSupplier);
            }
            int slIdx = -1;
            for (int i = 0; i < lstAllSupplier.size(); i++) {
                SupplierDto supplierDto = lstAllSupplier.get(i);
                if (supplierDto.getId() == selectedSupplier.getId()) {
                    slIdx = i;
                }
            }
            if(slIdx != -1){
                lstAllSupplier.set(slIdx, selectedSupplier);
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

    public SupplierDto getSelectedSupplier() {
        return selectedSupplier;
    }

    public void setSelectedSupplier(SupplierDto selectedSupplier) {
        this.selectedSupplier = selectedSupplier;
    }

    public List<SupplierDto> getLstAllSupplier() {
        return lstAllSupplier;
    }

    public void setLstAllSupplier(List<SupplierDto> lstAllSupplier) {
        this.lstAllSupplier = lstAllSupplier;
    }

    public List<SupplierDto> getLstFilteredSupplier() {
        return lstFilteredSupplier;
    }

    public void setLstFilteredSupplier(List<SupplierDto> lstFilteredSupplier) {
        this.lstFilteredSupplier = lstFilteredSupplier;
    }

    public List<EmployeeDto> getLstAllEmployees() {
        return lstAllEmployees;
    }

    public void setLstAllEmployees(List<EmployeeDto> lstAllEmployees) {
        this.lstAllEmployees = lstAllEmployees;
    }
}
