package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.model.*;
import com.kat.dmc.common.model.DepartmentDto;
import com.kat.dmc.common.util.RescusiveUtil;
import com.kat.dmc.common.util.SQLErrorUtil;
import com.kat.dmc.repository.interfaces.EmployeeRepo;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.EmployeeService;
import com.kat.dmc.service.interfaces.ObjectService;
import com.kat.dmc.service.interfaces.DeptService;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("deptMgr")
@ConversationScoped
public class DeptMgrController implements Serializable {

    @Autowired
    DeptService deptService;

    @Autowired
    ObjectService objectService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    UtilRepo utilRepo;

    private ControllerAction.State currentAct;
    private boolean disableAdd;
    private boolean disableCopy;
    private boolean disableEdit;
    private boolean disableDelete;
    private DepartmentDto selectedDept;
    private DepartmentDto tempDept;
    private List<DepartmentDto> lstAllDept;
    private List<DepartmentDto> lstFilteredDept;
    private TreeNode root;


    @PostConstruct
    public void init(){
        setCurrentAct(ControllerAction.State.VIEW);
        lstAllDept = deptService.findAll();
        root = new DefaultTreeNode(null, null);
        for(DepartmentDto departmentDto : lstAllDept){
            TreeNode deptRoot = new DefaultTreeNode(departmentDto, root);
            rescusiveDeptTree(deptRoot, departmentDto);
        }
    }

    private void rescusiveDeptTree(TreeNode deptRoot, DepartmentDto departmentDto){
        for(DepartmentDto child : departmentDto.getLstChildDept()){
            TreeNode deptChild = new DefaultTreeNode(child, deptRoot);
            rescusiveDeptTree(deptChild, child);
        }
    }

    public void selectDept(SelectEvent selectEvent){
        tempDept = (DepartmentDto) selectEvent.getObject();
        selectedDept = tempDept.clone();
        selectedDept.setLstEmployees(employeeService.findByDeptId(selectedDept.getId()));
    }

    public void actAdd(){
        setCurrentAct(ControllerAction.State.ADD);
        selectedDept = new DepartmentDto();
        selectedDept.setId(utilRepo.findSequenceNextval("dept__id_seq"));

        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actCopy(){
        selectedDept.setId(utilRepo.findSequenceNextval("dept__id_seq"));

        setCurrentAct(ControllerAction.State.COPY);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actEdit(){
        setCurrentAct(ControllerAction.State.EDIT);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actDelete(){
        try{
            deptService.delete(selectedDept.getId());
            lstAllDept.removeIf(s -> s.getId() == selectedDept.getId());
            selectedDept = null;
            root = new DefaultTreeNode(null, null);
            setCurrentAct(ControllerAction.State.VIEW);
            PrimeFaces.current().executeScript("PF('blkList').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO
                    , "Info", "Xoá thành công"));
        }catch (Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR
                    , SQLErrorUtil.getSQLError(ex.getMessage()), "Có lỗi xảy ra"));
        }
    }
    public void actBack(){
        selectedDept = tempDept;
        setCurrentAct(ControllerAction.State.VIEW);
        PrimeFaces.current().executeScript("PF('blkList').hide()");
    }
    public void actAccept(){
        try {
            deptService.save(selectedDept);

            tempDept = selectedDept;
            if (getCurrentAct() == ControllerAction.State.ADD || getCurrentAct() == ControllerAction.State.COPY) {
                lstAllDept.add(selectedDept);
            }
            setCurrentAct(ControllerAction.State.VIEW);
            PrimeFaces.current().executeScript("PF('blkList').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO
                    , "", "Lưu thông tin thành công"));
        }catch (Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR
                    , SQLErrorUtil.getSQLError(ex.getMessage()), "Có lỗi xảy ra"));
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

    public DepartmentDto getSelectedDept() {
        return selectedDept;
    }

    public void setSelectedDept(DepartmentDto selectedDept) {
        this.selectedDept = selectedDept;
    }

    public List<DepartmentDto> getLstAllDept() {
        return lstAllDept;
    }

    public void setLstAllDept(List<DepartmentDto> lstAllDept) {
        this.lstAllDept = lstAllDept;
    }

    public List<DepartmentDto> getLstFilteredDept() {
        return lstFilteredDept;
    }

    public void setLstFilteredDept(List<DepartmentDto> lstFilteredDept) {
        this.lstFilteredDept = lstFilteredDept;
    }

    public TreeNode getRoot() {
        return root;
    }
}
