package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.dto.ObjectDto;
import com.kat.dmc.common.dto.PropertyDto;
import com.kat.dmc.common.util.SQLErrorUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.ObjectService;
import com.kat.dmc.service.interfaces.PropertyService;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named("propertyMgr")
@ViewScoped
public class PropertyMgrController implements Serializable {

    @Autowired
    PropertyService propertyService;

    @Autowired
    ObjectService objectService;

    @Autowired
    UtilRepo utilRepo;

    private ControllerAction.State currentAct;
    private boolean disableAdd;
    private boolean disableCopy;
    private boolean disableEdit;
    private boolean disableDelete;
    private PropertyDto selectedProperty;
    private PropertyDto tempProperty;
    private PropertyDto searchProperty;
    private List<PropertyDto> lstAllProperty;
    private List<PropertyDto> lstFilteredProperty;


    @PostConstruct
    public void init(){
        setSearchProperty(new PropertyDto());
        setCurrentAct(ControllerAction.State.VIEW);
        lstAllProperty = propertyService.findAll();
    }


    public void selectProperty(SelectEvent selectEvent){
        tempProperty = (PropertyDto) selectEvent.getObject();
        selectedProperty = tempProperty.clone();
    }

    public void actSearch(){
        lstAllProperty = propertyService.findAllByReq(searchProperty);
    }

    public void actAdd(){
        setCurrentAct(ControllerAction.State.ADD);
        selectedProperty = new PropertyDto();
        selectedProperty.setId(utilRepo.findSequenceNextval("property__id_seq"));
        selectedProperty.setCode("US" + String.format("%06d", selectedProperty.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actCopy(){
        selectedProperty = selectedProperty.clone();
        selectedProperty.setId(utilRepo.findSequenceNextval("property__id_seq"));
        selectedProperty.setCode("US" + String.format("%06d", selectedProperty.getId()));
        setCurrentAct(ControllerAction.State.COPY);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actEdit(){
        setCurrentAct(ControllerAction.State.EDIT);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actDelete(){
        try{
            propertyService.delete(selectedProperty.getId());
            lstAllProperty.removeIf(s -> s.getId() == selectedProperty.getId());
            selectedProperty = null;
            setCurrentAct(ControllerAction.State.VIEW);
            PrimeFaces.current().executeScript("PF('blkList').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO
                    , "Info", "Xoá thành công"));
        }catch (Exception ex){
            Logger.getLogger(this.getClass().getName()).warning(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR
                    , SQLErrorUtil.getSQLError(ex), "Có lỗi xảy ra"));
        }
    }
    public void actBack(){
        selectedProperty = tempProperty;
        setCurrentAct(ControllerAction.State.VIEW);
        PrimeFaces.current().executeScript("PF('blkList').hide()");
    }
    public void actAccept(){
        try {
            propertyService.save(selectedProperty);
            tempProperty = selectedProperty;
            if (getCurrentAct() == ControllerAction.State.ADD || getCurrentAct() == ControllerAction.State.COPY) {
                lstAllProperty.add(selectedProperty);
            }

            int slIdx = -1;
            for (int i = 0; i < lstAllProperty.size(); i++) {
                PropertyDto clientDto = lstAllProperty.get(i);
                if (clientDto.getId() == selectedProperty.getId()) {
                    slIdx = i;
                }
            }
            if(slIdx != -1){
                lstAllProperty.set(slIdx, selectedProperty);
            }
            setCurrentAct(ControllerAction.State.VIEW);
            PrimeFaces.current().executeScript("PF('blkList').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO
                    , "", "Lưu thông tin thành công"));
        }catch (Exception ex){
            Logger.getLogger(this.getClass().getName()).warning(ex.getMessage());
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

    public PropertyDto getSelectedProperty() {
        return selectedProperty;
    }

    public void setSelectedProperty(PropertyDto selectedProperty) {
        this.selectedProperty = selectedProperty;
    }

    public List<PropertyDto> getLstAllProperty() {
        return lstAllProperty;
    }

    public void setLstAllProperty(List<PropertyDto> lstAllProperty) {
        this.lstAllProperty = lstAllProperty;
    }

    public List<PropertyDto> getLstFilteredProperty() {
        return lstFilteredProperty;
    }

    public void setLstFilteredProperty(List<PropertyDto> lstFilteredProperty) {
        this.lstFilteredProperty = lstFilteredProperty;
    }

    public PropertyDto getSearchProperty() {
        return searchProperty;
    }

    public void setSearchProperty(PropertyDto searchProperty) {
        this.searchProperty = searchProperty;
    }
}
