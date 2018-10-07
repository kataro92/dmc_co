package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.dto.EmployeeDto;
import com.kat.dmc.common.dto.ProductGroupDto;
import com.kat.dmc.common.dto.ProductSubgroupDto;
import com.kat.dmc.common.util.SQLErrorUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.EmployeeService;
import com.kat.dmc.service.interfaces.ProductGroupService;
import com.kat.dmc.service.interfaces.ProductSubgroupService;
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
import java.util.logging.Logger;

@Named("productGroupMgr")
@ViewScoped
public class ProductGroupMgrController implements Serializable {

    @Autowired
    ProductGroupService productGroupService;

    @Autowired
    ProductSubgroupService productSubgroupService;

    @Autowired
    UtilRepo utilRepo;

    @Autowired
    EmployeeService employeeService;

    private ControllerAction.State currentAct;
    private boolean disableAdd;
    private boolean disableCopy;
    private boolean disableEdit;
    private boolean disableDelete;
    private ProductGroupDto selectedProductGroup;
    private ProductGroupDto tempProductGroup;
    private List<ProductGroupDto> lstAllProductGroup;
    private List<ProductGroupDto> lstFilteredProductGroup;
    private List<EmployeeDto> lstAllEmployees;
    private List<ProductSubgroupDto> lstProductSubgroup;


    @PostConstruct
    public void init(){
        setCurrentAct(ControllerAction.State.VIEW);
        lstAllProductGroup = productGroupService.findAll();
        lstAllEmployees = employeeService.findAllActive();
    }

    public void selectProductGroup(SelectEvent selectEvent){
        tempProductGroup = (ProductGroupDto) selectEvent.getObject();
        selectedProductGroup = tempProductGroup.clone();
        setLstProductSubgroup(productSubgroupService.findAllByGroupId(selectedProductGroup.getId()));

    }

    public void actAdd(){
        setCurrentAct(ControllerAction.State.ADD);
        selectedProductGroup = new ProductGroupDto();
        selectedProductGroup.setId(utilRepo.findSequenceNextval("product_group__id_seq"));
        selectedProductGroup.setCode("NT" + String.format("%06d", selectedProductGroup.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actCopy(){
        setCurrentAct(ControllerAction.State.COPY);
        selectedProductGroup = selectedProductGroup.clone();
        selectedProductGroup.setId(utilRepo.findSequenceNextval("product_group__id_seq"));
        selectedProductGroup.setCode("NT" + String.format("%06d", selectedProductGroup.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actEdit(){
        setCurrentAct(ControllerAction.State.EDIT);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actDelete(){
        try{
            productGroupService.delete(selectedProductGroup.getId());
            lstAllProductGroup.removeIf(s -> s.getId() == selectedProductGroup.getId());
            selectedProductGroup = null;
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
        selectedProductGroup = tempProductGroup;
        setCurrentAct(ControllerAction.State.VIEW);
        PrimeFaces.current().executeScript("PF('blkList').hide()");
    }
    public void actAccept(){
        try {
            productGroupService.save(selectedProductGroup);
            tempProductGroup = selectedProductGroup;
            if (getCurrentAct() == ControllerAction.State.ADD || getCurrentAct() == ControllerAction.State.COPY) {
                lstAllProductGroup.add(selectedProductGroup);
            }
            int slIdx = -1;
            for (int i = 0; i < lstAllProductGroup.size(); i++) {
                ProductGroupDto productGroupDto = lstAllProductGroup.get(i);
                if (productGroupDto.getId() == selectedProductGroup.getId()) {
                    slIdx = i;
                }
            }
            if(slIdx != -1){
                lstAllProductGroup.set(slIdx, selectedProductGroup);
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

    public ProductGroupDto getSelectedProductGroup() {
        return selectedProductGroup;
    }

    public void setSelectedProductGroup(ProductGroupDto selectedProductGroup) {
        this.selectedProductGroup = selectedProductGroup;
    }

    public List<ProductGroupDto> getLstAllProductGroup() {
        return lstAllProductGroup;
    }

    public void setLstAllProductGroup(List<ProductGroupDto> lstAllProductGroup) {
        this.lstAllProductGroup = lstAllProductGroup;
    }

    public List<ProductGroupDto> getLstFilteredProductGroup() {
        return lstFilteredProductGroup;
    }

    public void setLstFilteredProductGroup(List<ProductGroupDto> lstFilteredProductGroup) {
        this.lstFilteredProductGroup = lstFilteredProductGroup;
    }

    public List<EmployeeDto> getLstAllEmployees() {
        return lstAllEmployees;
    }

    public void setLstAllEmployees(List<EmployeeDto> lstAllEmployees) {
        this.lstAllEmployees = lstAllEmployees;
    }

    public List<ProductSubgroupDto> getLstProductSubgroup() {
        return lstProductSubgroup;
    }

    public void setLstProductSubgroup(List<ProductSubgroupDto> lstProductSubgroup) {
        this.lstProductSubgroup = lstProductSubgroup;
    }
}
