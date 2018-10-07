package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.dto.ConfigDto;
import com.kat.dmc.common.dto.EmployeeDto;
import com.kat.dmc.common.util.SQLErrorUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.ConfigService;
import com.kat.dmc.service.interfaces.EmployeeService;
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
import java.util.logging.Logger;

@Named("configMgr")
@ConversationScoped
public class ConfigController implements Serializable {

    @Autowired
    ConfigService configService;

    @Autowired
    UtilRepo utilRepo;

    @Autowired
    EmployeeService employeeService;

    private ControllerAction.State currentAct;
    private boolean disableAdd;
    private boolean disableCopy;
    private boolean disableEdit;
    private boolean disableDelete;
    private ConfigDto selectedConfig;
    private ConfigDto tempConfig;
    private List<ConfigDto> lstAllConfig;
    private List<ConfigDto> lstFilteredConfig;
    private List<EmployeeDto> lstAllEmployees;


    @PostConstruct
    public void init(){
        setCurrentAct(ControllerAction.State.VIEW);
        lstAllConfig = configService.findAll();
        lstAllEmployees = employeeService.findAllActive();
    }

    public void selectConfig(SelectEvent selectEvent){
        tempConfig = (ConfigDto) selectEvent.getObject();
        selectedConfig = tempConfig.clone();
    }

    public void actAdd(){
        setCurrentAct(ControllerAction.State.ADD);
        selectedConfig = new ConfigDto();
        selectedConfig.setId(utilRepo.findSequenceNextval("dmc_config_id_seq"));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actCopy(){
        setCurrentAct(ControllerAction.State.COPY);
        selectedConfig = selectedConfig.clone();
        selectedConfig.setId(utilRepo.findSequenceNextval("dmc_config_id_seq"));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actEdit(){
        setCurrentAct(ControllerAction.State.EDIT);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actDelete(){
        try{
            configService.delete(selectedConfig.getId());
            lstAllConfig.removeIf(s -> s.getId() == selectedConfig.getId());
            selectedConfig = null;
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
        selectedConfig = tempConfig;
        setCurrentAct(ControllerAction.State.VIEW);
        PrimeFaces.current().executeScript("PF('blkList').hide()");
    }
    public void actAccept(){
        try {
            configService.save(selectedConfig);
            tempConfig = selectedConfig;
            if (getCurrentAct() == ControllerAction.State.ADD || getCurrentAct() == ControllerAction.State.COPY) {
                lstAllConfig.add(selectedConfig);
            }
            int slIdx = -1;
            for (int i = 0; i < lstAllConfig.size(); i++) {
                ConfigDto configDto = lstAllConfig.get(i);
                if (configDto.getId() == selectedConfig.getId()) {
                    slIdx = i;
                }
            }
            if(slIdx != -1){
                lstAllConfig.set(slIdx, selectedConfig);
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

    public ConfigDto getSelectedConfig() {
        return selectedConfig;
    }

    public void setSelectedConfig(ConfigDto selectedConfig) {
        this.selectedConfig = selectedConfig;
    }

    public List<ConfigDto> getLstAllConfig() {
        return lstAllConfig;
    }

    public void setLstAllConfig(List<ConfigDto> lstAllConfig) {
        this.lstAllConfig = lstAllConfig;
    }

    public List<ConfigDto> getLstFilteredConfig() {
        return lstFilteredConfig;
    }

    public void setLstFilteredConfig(List<ConfigDto> lstFilteredConfig) {
        this.lstFilteredConfig = lstFilteredConfig;
    }

    public List<EmployeeDto> getLstAllEmployees() {
        return lstAllEmployees;
    }

    public void setLstAllEmployees(List<EmployeeDto> lstAllEmployees) {
        this.lstAllEmployees = lstAllEmployees;
    }
}
