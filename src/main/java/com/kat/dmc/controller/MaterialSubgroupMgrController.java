package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.model.EmployeeDto;
import com.kat.dmc.common.model.MaterialDto;
import com.kat.dmc.common.model.MaterialSubgroupDto;
import com.kat.dmc.common.util.SQLErrorUtil;
import com.kat.dmc.repository.interfaces.MaterialRepo;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.EmployeeService;
import com.kat.dmc.service.interfaces.MaterialService;
import com.kat.dmc.service.interfaces.MaterialSubgroupService;
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

@Named("materialSubgroupMgr")
@ConversationScoped
public class MaterialSubgroupMgrController implements Serializable {

    @Autowired
    MaterialSubgroupService materialSubgroupService;

    @Autowired
    UtilRepo utilRepo;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    MaterialService materialService;

    private ControllerAction.State currentAct;
    private boolean disableAdd;
    private boolean disableCopy;
    private boolean disableEdit;
    private boolean disableDelete;
    private MaterialSubgroupDto selectedMaterialSubgroup;
    private MaterialSubgroupDto tempMaterialSubgroup;
    private List<MaterialSubgroupDto> lstAllMaterialSubgroup;
    private List<MaterialSubgroupDto> lstFilteredMaterialSubgroup;
    private List<EmployeeDto> lstAllEmployees;
    private List<MaterialDto> lstMaterial;


    @PostConstruct
    public void init(){
        setCurrentAct(ControllerAction.State.VIEW);
        lstAllMaterialSubgroup = materialSubgroupService.findAll();
        lstAllEmployees = employeeService.findAllActive();
    }

    public void selectMaterialSubgroup(SelectEvent selectEvent){
        tempMaterialSubgroup = (MaterialSubgroupDto) selectEvent.getObject();
        selectedMaterialSubgroup = tempMaterialSubgroup.clone();
        lstMaterial = materialService.findBySubgroupId(selectedMaterialSubgroup.getId());
    }

    public void actAdd(){
        setCurrentAct(ControllerAction.State.ADD);
        selectedMaterialSubgroup = new MaterialSubgroupDto();
        selectedMaterialSubgroup.setId(utilRepo.findSequenceNextval("material_subgroup__id_seq"));
        selectedMaterialSubgroup.setCode("NS" + String.format("%06d", selectedMaterialSubgroup.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actCopy(){
        setCurrentAct(ControllerAction.State.COPY);
        selectedMaterialSubgroup = selectedMaterialSubgroup.clone();
        selectedMaterialSubgroup.setId(utilRepo.findSequenceNextval("material_subgroup__id_seq"));
        selectedMaterialSubgroup.setCode("NS" + String.format("%06d", selectedMaterialSubgroup.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actEdit(){
        setCurrentAct(ControllerAction.State.EDIT);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actDelete(){
        try{
            materialSubgroupService.delete(selectedMaterialSubgroup.getId());
            lstAllMaterialSubgroup.removeIf(s -> s.getId() == selectedMaterialSubgroup.getId());
            selectedMaterialSubgroup = null;
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
        selectedMaterialSubgroup = tempMaterialSubgroup;
        setCurrentAct(ControllerAction.State.VIEW);
        PrimeFaces.current().executeScript("PF('blkList').hide()");
    }
    public void actAccept(){
        try {
            materialSubgroupService.save(selectedMaterialSubgroup);
            tempMaterialSubgroup = selectedMaterialSubgroup;
            if (getCurrentAct() == ControllerAction.State.ADD || getCurrentAct() == ControllerAction.State.COPY) {
                lstAllMaterialSubgroup.add(selectedMaterialSubgroup);
            }
            int slIdx = -1;
            for (int i = 0; i < lstAllMaterialSubgroup.size(); i++) {
                MaterialSubgroupDto materialSubgroupDto = lstAllMaterialSubgroup.get(i);
                if (materialSubgroupDto.getId() == selectedMaterialSubgroup.getId()) {
                    slIdx = i;
                }
            }
            if(slIdx != -1){
                lstAllMaterialSubgroup.set(slIdx, selectedMaterialSubgroup);
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

    public MaterialSubgroupDto getSelectedMaterialSubgroup() {
        return selectedMaterialSubgroup;
    }

    public void setSelectedMaterialSubgroup(MaterialSubgroupDto selectedMaterialSubgroup) {
        this.selectedMaterialSubgroup = selectedMaterialSubgroup;
    }

    public List<MaterialSubgroupDto> getLstAllMaterialSubgroup() {
        return lstAllMaterialSubgroup;
    }

    public void setLstAllMaterialSubgroup(List<MaterialSubgroupDto> lstAllMaterialSubgroup) {
        this.lstAllMaterialSubgroup = lstAllMaterialSubgroup;
    }

    public List<MaterialSubgroupDto> getLstFilteredMaterialSubgroup() {
        return lstFilteredMaterialSubgroup;
    }

    public void setLstFilteredMaterialSubgroup(List<MaterialSubgroupDto> lstFilteredMaterialSubgroup) {
        this.lstFilteredMaterialSubgroup = lstFilteredMaterialSubgroup;
    }

    public List<EmployeeDto> getLstAllEmployees() {
        return lstAllEmployees;
    }

    public void setLstAllEmployees(List<EmployeeDto> lstAllEmployees) {
        this.lstAllEmployees = lstAllEmployees;
    }

    public List<MaterialDto> getLstMaterial() {
        return lstMaterial;
    }

    public void setLstMaterial(List<MaterialDto> lstMaterial) {
        this.lstMaterial = lstMaterial;
    }
}
