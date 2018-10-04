package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.dto.ClientDto;
import com.kat.dmc.common.dto.EmployeeDto;
import com.kat.dmc.common.util.SQLErrorUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.ClientService;
import com.kat.dmc.service.interfaces.EmployeeService;
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

@Named("clientMgr")
@ViewScoped
public class ClientMgrController implements Serializable {

    @Autowired
    ClientService clientService;

    @Autowired
    UtilRepo utilRepo;

    @Autowired
    EmployeeService employeeService;

    private ControllerAction.State currentAct;
    private boolean disableAdd;
    private boolean disableCopy;
    private boolean disableEdit;
    private boolean disableDelete;
    private ClientDto selectedClient;
    private ClientDto tempClient;
    private List<ClientDto> lstAllClient;
    private List<ClientDto> lstFilteredClient;
    private List<EmployeeDto> lstAllEmployees;


    @PostConstruct
    public void init(){
        setCurrentAct(ControllerAction.State.VIEW);
        lstAllClient = clientService.findAll();
        lstAllEmployees = employeeService.findAllActive();
    }

    public void actChooseEmployee(Integer employeeId){
        for(EmployeeDto employeeDto : lstAllEmployees){
            if(employeeDto.getId() == employeeId){
                selectedClient.setEmpId(employeeId);
                selectedClient.setEmployeeCode(employeeDto.getCode());
                selectedClient.setEmpName(employeeDto.getName());
                selectedClient.setDeptId(employeeDto.getDeptId());
                selectedClient.setDeptName(employeeDto.getDeptName());
                selectedClient.setDefCode(employeeDto.getDefCode());
            }
        }
        PrimeFaces.current().executeScript("PF('pnlChooseEmp').hide()");
    }

    public void selectClient(SelectEvent selectEvent){
        tempClient = (ClientDto) selectEvent.getObject();
        selectedClient = tempClient.clone();
    }

    public void actAdd(){
        setCurrentAct(ControllerAction.State.ADD);
        selectedClient = new ClientDto();
        selectedClient.setId(utilRepo.findSequenceNextval("client__id_seq"));
        selectedClient.setCode("CL" + String.format("%06d", selectedClient.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actCopy(){
        setCurrentAct(ControllerAction.State.COPY);
        selectedClient = selectedClient.clone();
        selectedClient.setId(utilRepo.findSequenceNextval("client__id_seq"));
        selectedClient.setCode("CL" + String.format("%06d", selectedClient.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actEdit(){
        setCurrentAct(ControllerAction.State.EDIT);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actDelete(){
        try{
            clientService.delete(selectedClient.getId());
            lstAllClient.removeIf(s -> s.getId() == selectedClient.getId());
            selectedClient = null;
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
        selectedClient = tempClient;
        setCurrentAct(ControllerAction.State.VIEW);
        PrimeFaces.current().executeScript("PF('blkList').hide()");
    }
    public void actAccept(){
        try {
            clientService.save(selectedClient);
            tempClient = selectedClient;
            if (getCurrentAct() == ControllerAction.State.ADD || getCurrentAct() == ControllerAction.State.COPY) {
                lstAllClient.add(selectedClient);
            }
            int slIdx = -1;
            for (int i = 0; i < lstAllClient.size(); i++) {
                ClientDto clientDto = lstAllClient.get(i);
                if (clientDto.getId() == selectedClient.getId()) {
                    slIdx = i;
                }
            }
            if(slIdx != -1){
                lstAllClient.set(slIdx, selectedClient);
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

    public ClientDto getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(ClientDto selectedClient) {
        this.selectedClient = selectedClient;
    }

    public List<ClientDto> getLstAllClient() {
        return lstAllClient;
    }

    public void setLstAllClient(List<ClientDto> lstAllClient) {
        this.lstAllClient = lstAllClient;
    }

    public List<ClientDto> getLstFilteredClient() {
        return lstFilteredClient;
    }

    public void setLstFilteredClient(List<ClientDto> lstFilteredClient) {
        this.lstFilteredClient = lstFilteredClient;
    }

    public List<EmployeeDto> getLstAllEmployees() {
        return lstAllEmployees;
    }

    public void setLstAllEmployees(List<EmployeeDto> lstAllEmployees) {
        this.lstAllEmployees = lstAllEmployees;
    }
}
