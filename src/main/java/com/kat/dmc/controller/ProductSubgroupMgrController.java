package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.model.EmployeeDto;
import com.kat.dmc.common.model.ProductDto;
import com.kat.dmc.common.model.ProductSubgroupDto;
import com.kat.dmc.common.util.SQLErrorUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.EmployeeService;
import com.kat.dmc.service.interfaces.ProductService;
import com.kat.dmc.service.interfaces.ProductSubgroupService;
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

@Named("productSubgroupMgr")
@ConversationScoped
public class ProductSubgroupMgrController implements Serializable {

    @Autowired
    ProductSubgroupService productSubgroupService;

    @Autowired
    UtilRepo utilRepo;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ProductService productService;

    private ControllerAction.State currentAct;
    private boolean disableAdd;
    private boolean disableCopy;
    private boolean disableEdit;
    private boolean disableDelete;
    private ProductSubgroupDto selectedProductSubgroup;
    private ProductSubgroupDto tempProductSubgroup;
    private List<ProductSubgroupDto> lstAllProductSubgroup;
    private List<ProductSubgroupDto> lstFilteredProductSubgroup;
    private List<EmployeeDto> lstAllEmployees;
    private List<ProductDto> lstProduct;


    @PostConstruct
    public void init(){
        setCurrentAct(ControllerAction.State.VIEW);
        lstAllProductSubgroup = productSubgroupService.findAll();
        lstAllEmployees = employeeService.findAllActive();
    }

    public void selectProductSubgroup(SelectEvent selectEvent){
        tempProductSubgroup = (ProductSubgroupDto) selectEvent.getObject();
        selectedProductSubgroup = tempProductSubgroup.clone();
        lstProduct = productService.findBySubgroupId(selectedProductSubgroup.getId());
    }

    public void actAdd(){
        setCurrentAct(ControllerAction.State.ADD);
        selectedProductSubgroup = new ProductSubgroupDto();
        selectedProductSubgroup.setId(utilRepo.findSequenceNextval("product_subgroup__id_seq"));
        selectedProductSubgroup.setCode("NS" + String.format("%06d", selectedProductSubgroup.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actCopy(){
        setCurrentAct(ControllerAction.State.COPY);
        selectedProductSubgroup = selectedProductSubgroup.clone();
        selectedProductSubgroup.setId(utilRepo.findSequenceNextval("product_subgroup__id_seq"));
        selectedProductSubgroup.setCode("NS" + String.format("%06d", selectedProductSubgroup.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actEdit(){
        setCurrentAct(ControllerAction.State.EDIT);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actDelete(){
        try{
            productSubgroupService.delete(selectedProductSubgroup.getId());
            lstAllProductSubgroup.removeIf(s -> s.getId() == selectedProductSubgroup.getId());
            selectedProductSubgroup = null;
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
        selectedProductSubgroup = tempProductSubgroup;
        setCurrentAct(ControllerAction.State.VIEW);
        PrimeFaces.current().executeScript("PF('blkList').hide()");
    }
    public void actAccept(){
        try {
            productSubgroupService.save(selectedProductSubgroup);
            tempProductSubgroup = selectedProductSubgroup;
            if (getCurrentAct() == ControllerAction.State.ADD || getCurrentAct() == ControllerAction.State.COPY) {
                lstAllProductSubgroup.add(selectedProductSubgroup);
            }
            int slIdx = -1;
            for (int i = 0; i < lstAllProductSubgroup.size(); i++) {
                ProductSubgroupDto productSubgroupDto = lstAllProductSubgroup.get(i);
                if (productSubgroupDto.getId() == selectedProductSubgroup.getId()) {
                    slIdx = i;
                }
            }
            if(slIdx != -1){
                lstAllProductSubgroup.set(slIdx, selectedProductSubgroup);
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

    public ProductSubgroupDto getSelectedProductSubgroup() {
        return selectedProductSubgroup;
    }

    public void setSelectedProductSubgroup(ProductSubgroupDto selectedProductSubgroup) {
        this.selectedProductSubgroup = selectedProductSubgroup;
    }

    public List<ProductSubgroupDto> getLstAllProductSubgroup() {
        return lstAllProductSubgroup;
    }

    public void setLstAllProductSubgroup(List<ProductSubgroupDto> lstAllProductSubgroup) {
        this.lstAllProductSubgroup = lstAllProductSubgroup;
    }

    public List<ProductSubgroupDto> getLstFilteredProductSubgroup() {
        return lstFilteredProductSubgroup;
    }

    public void setLstFilteredProductSubgroup(List<ProductSubgroupDto> lstFilteredProductSubgroup) {
        this.lstFilteredProductSubgroup = lstFilteredProductSubgroup;
    }

    public List<EmployeeDto> getLstAllEmployees() {
        return lstAllEmployees;
    }

    public void setLstAllEmployees(List<EmployeeDto> lstAllEmployees) {
        this.lstAllEmployees = lstAllEmployees;
    }

    public List<ProductDto> getLstProduct() {
        return lstProduct;
    }

    public void setLstProduct(List<ProductDto> lstProduct) {
        this.lstProduct = lstProduct;
    }
}
