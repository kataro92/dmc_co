package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.dto.EmployeeDto;
import com.kat.dmc.common.dto.ObjectDto;
import com.kat.dmc.common.util.RescusiveUtil;
import com.kat.dmc.common.util.SQLErrorUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.EmployeeService;
import com.kat.dmc.service.interfaces.ObjectService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named("userMgr")
@ViewScoped
public class EmployeeMgrController implements Serializable {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ObjectService objectService;

    @Autowired
    UtilRepo utilRepo;

    private ControllerAction.State currentAct;
    private boolean disableAdd;
    private boolean disableCopy;
    private boolean disableEdit;
    private boolean disableDelete;
    private EmployeeDto selectedUser;
    private EmployeeDto tempUser;
    private List<EmployeeDto> lstAllUser;
    private List<EmployeeDto> lstFilteredUser;
    private List<ObjectDto> lstObject;
    private List<ObjectDto> lstPermission;
    private TreeNode root;


    @PostConstruct
    public void init(){
        setCurrentAct(ControllerAction.State.VIEW);
        lstAllUser = employeeService.findAll();
        lstObject = objectService.findAll();
        root = new DefaultTreeNode(null, null);
    }

    private TreeNode lstObject2Tree(List<ObjectDto> lstPermission){
        TreeNode root = new DefaultTreeNode(new ObjectDto(), null);
        for(ObjectDto objectDto : lstPermission){
            TreeNode firstChild = new DefaultTreeNode(objectDto.clone(), root);
            recusiveTreeObject(firstChild);
        }
        return root;
    }

    private void recusiveTreeObject(TreeNode firstChild){
        ObjectDto data = (ObjectDto) firstChild.getData();
        if(data.getLstChildObject() != null){
            for(ObjectDto objectDto : data.getLstChildObject()){
                TreeNode levelChild = new DefaultTreeNode(objectDto.clone(), firstChild);
                recusiveTreeObject(levelChild);
            }
        }
    }


    public void selectUser(SelectEvent selectEvent){
        tempUser = (EmployeeDto) selectEvent.getObject();
        selectedUser = tempUser.clone();
        List<Integer> objectIDHasPermission = objectService.findObjectIdByUserId(selectedUser.getId());
        lstPermission = new ArrayList<>();
        lstObject.forEach(objectDto -> lstPermission.add(objectDto.clone()));
        RescusiveUtil.rescusiveSetPermission(lstPermission, objectIDHasPermission);
        root = lstObject2Tree(lstPermission);
    }

    public void actAdd(){
        setCurrentAct(ControllerAction.State.ADD);
        selectedUser = new EmployeeDto();
        selectedUser.setId(utilRepo.findSequenceNextval("user__id_seq"));
        selectedUser.setCode("US" + String.format("%06d", selectedUser.getId()));
        lstPermission = new ArrayList<>();
        lstObject.forEach(objectDto -> lstPermission.add(objectDto.clone()));
        RescusiveUtil.rescusiveSetPermission(lstPermission, new ArrayList<>());
        root = lstObject2Tree(lstPermission);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actCopy(int userId){
        for(EmployeeDto employeeDto : lstAllUser){
            if(employeeDto.getId() == userId){
                selectedUser = employeeDto.clone();
            }
        }
        selectedUser.setId(utilRepo.findSequenceNextval("user__id_seq"));
        selectedUser.setCode("US" + String.format("%06d", selectedUser.getId()));
        lstPermission = new ArrayList<>();
        lstObject.forEach(objectDto -> lstPermission.add(objectDto.clone()));
        RescusiveUtil.rescusiveSetPermission(lstPermission, new ArrayList<>());
        root = lstObject2Tree(lstPermission);
        setCurrentAct(ControllerAction.State.COPY);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actEdit(int userId){
        for(EmployeeDto employeeDto : lstAllUser){
            if(employeeDto.getId() == userId){
                selectedUser = employeeDto.clone();
            }
        }
        setCurrentAct(ControllerAction.State.EDIT);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actDelete(Integer userId){
        try{
            employeeService.delete(userId);
            lstAllUser.removeIf(s -> s.getId() == selectedUser.getId());
            selectedUser = null;
            root = new DefaultTreeNode(null, null);
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
        selectedUser = tempUser;
        setCurrentAct(ControllerAction.State.VIEW);
        PrimeFaces.current().executeScript("PF('blkList').hide()");
    }
    public void actAccept(){
        try {
            employeeService.save(selectedUser);
            List<TreeNode> children = root.getChildren();
            List<ObjectDto> lstNewPermission = new ArrayList<>();
            for (TreeNode treeNode : children) {
                rescusiveGetObject(treeNode, lstNewPermission);
            }
            objectService.saveNewPermission(lstNewPermission, selectedUser.getId());
            tempUser = selectedUser;
            if (getCurrentAct() == ControllerAction.State.ADD || getCurrentAct() == ControllerAction.State.COPY) {
                lstAllUser.add(selectedUser);
            }

            int slIdx = -1;
            for (int i = 0; i < lstAllUser.size(); i++) {
                EmployeeDto clientDto = lstAllUser.get(i);
                if (clientDto.getId() == selectedUser.getId()) {
                    slIdx = i;
                }
            }
            if(slIdx != -1){
                lstAllUser.set(slIdx, selectedUser);
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

    private void rescusiveGetObject(TreeNode treeNode, List<ObjectDto> currentPermission){
        ObjectDto childNode = (ObjectDto) treeNode.getData();
        if(childNode.isHasPermission()) {
            currentPermission.add(childNode.cloneParent());
        }
        if(treeNode.getChildren() != null){
            for(TreeNode dto : treeNode.getChildren()){
                rescusiveGetObject(dto, currentPermission);
            }
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

    public EmployeeDto getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(EmployeeDto selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<EmployeeDto> getLstAllUser() {
        return lstAllUser;
    }

    public void setLstAllUser(List<EmployeeDto> lstAllUser) {
        this.lstAllUser = lstAllUser;
    }

    public List<EmployeeDto> getLstFilteredUser() {
        return lstFilteredUser;
    }

    public void setLstFilteredUser(List<EmployeeDto> lstFilteredUser) {
        this.lstFilteredUser = lstFilteredUser;
    }

    public List<ObjectDto> getLstObject() {
        return lstObject;
    }

    public void setLstObject(List<ObjectDto> lstObject) {
        this.lstObject = lstObject;
    }

    public List<ObjectDto> getLstPermission() {
        return lstPermission;
    }

    public void setLstPermission(List<ObjectDto> lstPermission) {
        this.lstPermission = lstPermission;
    }

    public TreeNode getRoot() {
        return root;
    }
}
