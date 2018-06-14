package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.model.EmployeeDto;
import com.kat.dmc.common.model.MaterialGroupDto;
import com.kat.dmc.common.model.MaterialSubgroupDto;
import com.kat.dmc.common.util.SQLErrorUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.EmployeeService;
import com.kat.dmc.service.interfaces.MaterialGroupService;
import com.kat.dmc.service.interfaces.MaterialSubgroupService;
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

@Named("materialGroupMgr")
@ViewScoped
public class MaterialGroupMgrController implements Serializable {

    @Autowired
    MaterialGroupService materialGroupService;

    @Autowired
    MaterialSubgroupService materialSubgroupService;

    @Autowired
    UtilRepo utilRepo;

    @Autowired
    EmployeeService employeeService;

    private ControllerAction.State currentAct;
    private boolean disableAdd;
    private boolean disableCopy;
    private boolean disableEdit;
    private boolean disableDelete;
    private MaterialGroupDto selectedMaterialGroup;
    private MaterialGroupDto tempMaterialGroup;
    private List<MaterialGroupDto> lstAllMaterialGroup;
    private List<MaterialGroupDto> lstFilteredMaterialGroup;
    private List<EmployeeDto> lstAllEmployees;
    private List<MaterialSubgroupDto> lstMaterialSubgroup;


    @PostConstruct
    public void init(){
        setCurrentAct(ControllerAction.State.VIEW);
        lstAllMaterialGroup = materialGroupService.findAll();
        lstAllEmployees = employeeService.findAllActive();
    }

    public void selectMaterialGroup(SelectEvent selectEvent){
        tempMaterialGroup = (MaterialGroupDto) selectEvent.getObject();
        selectedMaterialGroup = tempMaterialGroup.clone();
        setLstMaterialSubgroup(materialSubgroupService.findAllByGroupId(selectedMaterialGroup.getId()));

    }

    public void actAdd(){
        setCurrentAct(ControllerAction.State.ADD);
        selectedMaterialGroup = new MaterialGroupDto();
        selectedMaterialGroup.setId(utilRepo.findSequenceNextval("material_group__id_seq"));
        selectedMaterialGroup.setCode("NT" + String.format("%06d", selectedMaterialGroup.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actCopy(){
        setCurrentAct(ControllerAction.State.COPY);
        selectedMaterialGroup = selectedMaterialGroup.clone();
        selectedMaterialGroup.setId(utilRepo.findSequenceNextval("material_group__id_seq"));
        selectedMaterialGroup.setCode("NT" + String.format("%06d", selectedMaterialGroup.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actEdit(){
        setCurrentAct(ControllerAction.State.EDIT);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actDelete(){
        try{
            materialGroupService.delete(selectedMaterialGroup.getId());
            lstAllMaterialGroup.removeIf(s -> s.getId() == selectedMaterialGroup.getId());
            selectedMaterialGroup = null;
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
        selectedMaterialGroup = tempMaterialGroup;
        setCurrentAct(ControllerAction.State.VIEW);
        PrimeFaces.current().executeScript("PF('blkList').hide()");
    }
    public void actAccept(){
        try {
            materialGroupService.save(selectedMaterialGroup);
            tempMaterialGroup = selectedMaterialGroup;
            if (getCurrentAct() == ControllerAction.State.ADD || getCurrentAct() == ControllerAction.State.COPY) {
                lstAllMaterialGroup.add(selectedMaterialGroup);
            }
            int slIdx = -1;
            for (int i = 0; i < lstAllMaterialGroup.size(); i++) {
                MaterialGroupDto materialGroupDto = lstAllMaterialGroup.get(i);
                if (materialGroupDto.getId() == selectedMaterialGroup.getId()) {
                    slIdx = i;
                }
            }
            if(slIdx != -1){
                lstAllMaterialGroup.set(slIdx, selectedMaterialGroup);
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

    public MaterialGroupDto getSelectedMaterialGroup() {
        return selectedMaterialGroup;
    }

    public void setSelectedMaterialGroup(MaterialGroupDto selectedMaterialGroup) {
        this.selectedMaterialGroup = selectedMaterialGroup;
    }

    public List<MaterialGroupDto> getLstAllMaterialGroup() {
        return lstAllMaterialGroup;
    }

    public void setLstAllMaterialGroup(List<MaterialGroupDto> lstAllMaterialGroup) {
        this.lstAllMaterialGroup = lstAllMaterialGroup;
    }

    public List<MaterialGroupDto> getLstFilteredMaterialGroup() {
        return lstFilteredMaterialGroup;
    }

    public void setLstFilteredMaterialGroup(List<MaterialGroupDto> lstFilteredMaterialGroup) {
        this.lstFilteredMaterialGroup = lstFilteredMaterialGroup;
    }

    public List<EmployeeDto> getLstAllEmployees() {
        return lstAllEmployees;
    }

    public void setLstAllEmployees(List<EmployeeDto> lstAllEmployees) {
        this.lstAllEmployees = lstAllEmployees;
    }

    public List<MaterialSubgroupDto> getLstMaterialSubgroup() {
        return lstMaterialSubgroup;
    }

    public void setLstMaterialSubgroup(List<MaterialSubgroupDto> lstMaterialSubgroup) {
        this.lstMaterialSubgroup = lstMaterialSubgroup;
    }
}
