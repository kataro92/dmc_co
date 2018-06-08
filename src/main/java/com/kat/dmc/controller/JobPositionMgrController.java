package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.model.EmployeeDto;
import com.kat.dmc.common.model.JobPositionDto;
import com.kat.dmc.common.util.SQLErrorUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.EmployeeService;
import com.kat.dmc.service.interfaces.JobPositionService;
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

@Named("jobPositionMgr")
@ConversationScoped
public class JobPositionMgrController implements Serializable {

    @Autowired
    JobPositionService jobPositionService;

    @Autowired
    UtilRepo utilRepo;

    @Autowired
    EmployeeService employeeService;

    private ControllerAction.State currentAct;
    private boolean disableAdd;
    private boolean disableCopy;
    private boolean disableEdit;
    private boolean disableDelete;
    private JobPositionDto selectedJobPosition;
    private JobPositionDto tempJobPosition;
    private List<JobPositionDto> lstAllJobPosition;
    private List<JobPositionDto> lstFilteredJobPosition;
    private List<EmployeeDto> lstAllEmployees;


    @PostConstruct
    public void init(){
        setCurrentAct(ControllerAction.State.VIEW);
        lstAllJobPosition = jobPositionService.findAll();
        lstAllEmployees = employeeService.findAllActive();
    }

    public void selectJobPosition(SelectEvent selectEvent){
        tempJobPosition = (JobPositionDto) selectEvent.getObject();
        selectedJobPosition = tempJobPosition.clone();
    }

    public void actAdd(){
        setCurrentAct(ControllerAction.State.ADD);
        selectedJobPosition = new JobPositionDto();
        selectedJobPosition.setId(utilRepo.findSequenceNextval("job_position__id_seq"));
        selectedJobPosition.setCode("CL" + String.format("%06d", selectedJobPosition.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actCopy(){
        setCurrentAct(ControllerAction.State.COPY);
        selectedJobPosition = selectedJobPosition.clone();
        selectedJobPosition.setId(utilRepo.findSequenceNextval("job_position__id_seq"));
        selectedJobPosition.setCode("CL" + String.format("%06d", selectedJobPosition.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actEdit(){
        setCurrentAct(ControllerAction.State.EDIT);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actDelete(){
        try{
            jobPositionService.delete(selectedJobPosition.getId());
            lstAllJobPosition.removeIf(s -> s.getId() == selectedJobPosition.getId());
            selectedJobPosition = null;
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
        selectedJobPosition = tempJobPosition;
        setCurrentAct(ControllerAction.State.VIEW);
        PrimeFaces.current().executeScript("PF('blkList').hide()");
    }
    public void actAccept(){
        try {
            jobPositionService.save(selectedJobPosition);
            tempJobPosition = selectedJobPosition;
            if (getCurrentAct() == ControllerAction.State.ADD || getCurrentAct() == ControllerAction.State.COPY) {
                lstAllJobPosition.add(selectedJobPosition);
            }
            int slIdx = -1;
            for (int i = 0; i < lstAllJobPosition.size(); i++) {
                JobPositionDto jobPositionDto = lstAllJobPosition.get(i);
                if (jobPositionDto.getId() == selectedJobPosition.getId()) {
                    slIdx = i;
                }
            }
            if(slIdx != -1){
                lstAllJobPosition.set(slIdx, selectedJobPosition);
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

    public JobPositionDto getSelectedJobPosition() {
        return selectedJobPosition;
    }

    public void setSelectedJobPosition(JobPositionDto selectedJobPosition) {
        this.selectedJobPosition = selectedJobPosition;
    }

    public List<JobPositionDto> getLstAllJobPosition() {
        return lstAllJobPosition;
    }

    public void setLstAllJobPosition(List<JobPositionDto> lstAllJobPosition) {
        this.lstAllJobPosition = lstAllJobPosition;
    }

    public List<JobPositionDto> getLstFilteredJobPosition() {
        return lstFilteredJobPosition;
    }

    public void setLstFilteredJobPosition(List<JobPositionDto> lstFilteredJobPosition) {
        this.lstFilteredJobPosition = lstFilteredJobPosition;
    }

    public List<EmployeeDto> getLstAllEmployees() {
        return lstAllEmployees;
    }

    public void setLstAllEmployees(List<EmployeeDto> lstAllEmployees) {
        this.lstAllEmployees = lstAllEmployees;
    }
}
