package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.model.*;
import com.kat.dmc.common.util.SQLErrorUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.EmployeeService;
import com.kat.dmc.service.interfaces.ProductGroupService;
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
import java.util.ArrayList;
import java.util.List;

@Named("productMgr")
@ConversationScoped
public class ProductMgrController implements Serializable {

    @Autowired
    ProductService productService;

    @Autowired
    UtilRepo utilRepo;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ProductGroupService productGroupService;

    @Autowired
    ProductSubgroupService productSubgroupService;

    private ControllerAction.State currentAct;
    private boolean disableAdd;
    private boolean disableCopy;
    private boolean disableEdit;
    private boolean disableDelete;
    private ProductDto selectedProduct;
    private ProductDto tempProduct;
    private List<ProductDto> lstAllProduct;
    private List<ProductDto> lstFilteredProduct;
    private List<EmployeeDto> lstAllEmployees;

    private List<ProductGroupDto> lstProductGroup;
    private List<ProductSubgroupDto> lstProductSubgroup;
    private List<ProductSubgroupDto> lstFileredProductSubgroup;

    private List<MaterialDto> lstMaterial;


    @PostConstruct
    public void init(){
        setCurrentAct(ControllerAction.State.VIEW);
        lstAllProduct = productService.findAll();
        lstAllEmployees = employeeService.findAllActive();
        lstProductGroup = productGroupService.findAllActive();
        lstProductSubgroup = productSubgroupService.findAllActive();
    }

    public void selectProductGroup(){
        lstFileredProductSubgroup = new ArrayList<>();
        for(ProductSubgroupDto productSubgroupDto : lstProductSubgroup){
            if(productSubgroupDto.getId() == Integer.parseInt(selectedProduct.getProductGroupCode())){
                lstFileredProductSubgroup.add(productSubgroupDto);
            }
        }
    }

    public void selectProduct(SelectEvent selectEvent){
        tempProduct = (ProductDto) selectEvent.getObject();
        selectedProduct = tempProduct.clone();
        selectProductGroup();
    }

    public void actAdd(){
        setCurrentAct(ControllerAction.State.ADD);
        selectedProduct = new ProductDto();
        selectedProduct.setId(utilRepo.findSequenceNextval("product__id_seq"));
        selectedProduct.setCode("VT" + String.format("%06d", selectedProduct.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actCopy(){
        setCurrentAct(ControllerAction.State.COPY);
        selectedProduct = selectedProduct.clone();
        selectedProduct.setId(utilRepo.findSequenceNextval("product__id_seq"));
        selectedProduct.setCode("VT" + String.format("%06d", selectedProduct.getId()));
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actEdit(){
        setCurrentAct(ControllerAction.State.EDIT);
        PrimeFaces.current().executeScript("PF('blkList').show()");
    }
    public void actDelete(){
        try{
            productService.delete(selectedProduct.getId());
            lstAllProduct.removeIf(s -> s.getId() == selectedProduct.getId());
            selectedProduct = null;
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
        selectedProduct = tempProduct;
        setCurrentAct(ControllerAction.State.VIEW);
        PrimeFaces.current().executeScript("PF('blkList').hide()");
    }
    public void actAccept(){
        try {
            productService.save(selectedProduct);
            tempProduct = selectedProduct;
            if (getCurrentAct() == ControllerAction.State.ADD || getCurrentAct() == ControllerAction.State.COPY) {
                lstAllProduct.add(selectedProduct);
            }
            int slIdx = -1;
            for (int i = 0; i < lstAllProduct.size(); i++) {
                ProductDto productDto = lstAllProduct.get(i);
                if (productDto.getId() == selectedProduct.getId()) {
                    slIdx = i;
                }
            }
            if(slIdx != -1){
                lstAllProduct.set(slIdx, selectedProduct);
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

    public ProductDto getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductDto selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<ProductDto> getLstAllProduct() {
        return lstAllProduct;
    }

    public void setLstAllProduct(List<ProductDto> lstAllProduct) {
        this.lstAllProduct = lstAllProduct;
    }

    public List<ProductDto> getLstFilteredProduct() {
        return lstFilteredProduct;
    }

    public void setLstFilteredProduct(List<ProductDto> lstFilteredProduct) {
        this.lstFilteredProduct = lstFilteredProduct;
    }

    public List<EmployeeDto> getLstAllEmployees() {
        return lstAllEmployees;
    }

    public void setLstAllEmployees(List<EmployeeDto> lstAllEmployees) {
        this.lstAllEmployees = lstAllEmployees;
    }

    public List<ProductGroupDto> getLstProductGroup() {
        return lstProductGroup;
    }

    public void setLstProductGroup(List<ProductGroupDto> lstProductGroup) {
        this.lstProductGroup = lstProductGroup;
    }

    public List<ProductSubgroupDto> getLstFileredProductSubgroup() {
        return lstFileredProductSubgroup;
    }

    public void setLstFileredProductSubgroup(List<ProductSubgroupDto> lstFileredProductSubgroup) {
        this.lstFileredProductSubgroup = lstFileredProductSubgroup;
    }

    public List<MaterialDto> getLstMaterial() {
        return lstMaterial;
    }

    public void setLstMaterial(List<MaterialDto> lstMaterial) {
        this.lstMaterial = lstMaterial;
    }
}