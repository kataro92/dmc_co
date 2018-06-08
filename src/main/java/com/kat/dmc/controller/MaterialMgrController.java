package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.model.EmployeeDto;
import com.kat.dmc.common.model.MaterialDto;
import com.kat.dmc.common.model.MaterialGroupDto;
import com.kat.dmc.common.model.MaterialSubgroupDto;
import com.kat.dmc.common.util.SQLErrorUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.EmployeeService;
import com.kat.dmc.service.interfaces.MaterialGroupService;
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
import java.util.ArrayList;
import java.util.List;

@Named("materialMgr")
@ConversationScoped
public class MaterialMgrController implements Serializable {

    @Autowired
    MaterialService materialService;

    @Autowired
    UtilRepo utilRepo;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    MaterialGroupService materialGroupService;

    @Autowired
    MaterialSubgroupService materialSubgroupService;

    private ControllerAction.State currentAct;
    private boolean disableAdd;
    private boolean disableCopy;
    private boolean disableEdit;
    private boolean disableDelete;
    private MaterialDto selectedMaterial;
    private MaterialDto tempMaterial;
    private List<MaterialDto> lstAllMaterial;
    private List<MaterialDto> lstFilteredMaterial;
    private List<EmployeeDto> lstAllEmployees;

    private List<MaterialGroupDto> lstMaterialGroup;
    private List<MaterialSubgroupDto> lstMaterialSubgroup;
    private List<MaterialSubgroupDto> lstFileredMaterialSubgroup;


    @PostConstruct
    public void init(){
        setCurrentAct(ControllerAction.State.VIEW);
        lstAllMaterial = materialService.findAll();
        lstAllEmployees = employeeService.findAllActive();
        lstMaterialGroup = materialGroupService.findAllActive();
        lstMaterialSubgroup = materialSubgroupService.findAllActive();
    }

    public void selectMaterialGroup(){
        lstFileredMaterialSubgroup = new ArrayList<>();
        for(MaterialSubgroupDto materialSubgroupDto : lstMaterialSubgroup){
            if(materialSubgroupDto.getId() == Integer.parseInt(selectedMaterial.getMaterialGroupCode())){
                lstFileredMaterialSubgroup.add(materialSubgroupDto);
            }
        }
    }

    public void selectMaterial(SelectEvent selectEvent){
        tempMaterial = (MaterialDto) selectEvent.getObject();
        selectedMaterial = tempMaterial.clone();
        selectMaterialGroup();
    }

    public void actAdd(){
        setCurrentAct(ControllerAction.State.ADD);
        selectedMaterial = new MaterialDto();
        selectedMaterial.setId(utilRepo.findSequenceNextval("material__id_seq"));
        selectedMaterial.setCode("VT" + String.format("%06d", selectedMaterial.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actCopy(){
        setCurrentAct(ControllerAction.State.COPY);
        selectedMaterial = selectedMaterial.clone();
        selectedMaterial.setId(utilRepo.findSequenceNextval("material__id_seq"));
        selectedMaterial.setCode("VT" + String.format("%06d", selectedMaterial.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actEdit(){
        setCurrentAct(ControllerAction.State.EDIT);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actDelete(){
        try{
            materialService.delete(selectedMaterial.getId());
            lstAllMaterial.removeIf(s -> s.getId() == selectedMaterial.getId());
            selectedMaterial = null;
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
        selectedMaterial = tempMaterial;
        setCurrentAct(ControllerAction.State.VIEW);
        PrimeFaces.current().executeScript("PF('blkList').hide()");
    }
    public void actAccept(){
        try {
            materialService.save(selectedMaterial);
            tempMaterial = selectedMaterial;
            if (getCurrentAct() == ControllerAction.State.ADD || getCurrentAct() == ControllerAction.State.COPY) {
                lstAllMaterial.add(selectedMaterial);
            }
            int slIdx = -1;
            for (int i = 0; i < lstAllMaterial.size(); i++) {
                MaterialDto materialDto = lstAllMaterial.get(i);
                if (materialDto.getId() == selectedMaterial.getId()) {
                    slIdx = i;
                }
            }
            if(slIdx != -1){
                lstAllMaterial.set(slIdx, selectedMaterial);
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

    public MaterialDto getSelectedMaterial() {
        return selectedMaterial;
    }

    public void setSelectedMaterial(MaterialDto selectedMaterial) {
        this.selectedMaterial = selectedMaterial;
    }

    public List<MaterialDto> getLstAllMaterial() {
        return lstAllMaterial;
    }

    public void setLstAllMaterial(List<MaterialDto> lstAllMaterial) {
        this.lstAllMaterial = lstAllMaterial;
    }

    public List<MaterialDto> getLstFilteredMaterial() {
        return lstFilteredMaterial;
    }

    public void setLstFilteredMaterial(List<MaterialDto> lstFilteredMaterial) {
        this.lstFilteredMaterial = lstFilteredMaterial;
    }

    public List<EmployeeDto> getLstAllEmployees() {
        return lstAllEmployees;
    }

    public void setLstAllEmployees(List<EmployeeDto> lstAllEmployees) {
        this.lstAllEmployees = lstAllEmployees;
    }

    public List<MaterialGroupDto> getLstMaterialGroup() {
        return lstMaterialGroup;
    }

    public void setLstMaterialGroup(List<MaterialGroupDto> lstMaterialGroup) {
        this.lstMaterialGroup = lstMaterialGroup;
    }

    public List<MaterialSubgroupDto> getLstFileredMaterialSubgroup() {
        return lstFileredMaterialSubgroup;
    }

    public void setLstFileredMaterialSubgroup(List<MaterialSubgroupDto> lstFileredMaterialSubgroup) {
        this.lstFileredMaterialSubgroup = lstFileredMaterialSubgroup;
    }
}
