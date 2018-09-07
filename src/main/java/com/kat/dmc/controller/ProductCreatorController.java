package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.dto.*;
import com.kat.dmc.common.util.DateUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.*;
import org.modelmapper.ModelMapper;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("productCreator")
@ViewScoped
public class ProductCreatorController implements Serializable {

    @Autowired
    MaterialService materialService;

    @Autowired
    UtilRepo utilRepo;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    MaterialGroupService materialGroupService;

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    MaterialSubgroupService materialSubgroupService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProductService productService;

    @Autowired
    ProductGroupService productGroupService;

    @Autowired
    ProductSubgroupService productSubgroupService;

    @Autowired
    BuildingService buildingService;

    @Autowired
    WarehouseImportService warehouseImportService;

    private ControllerAction.State currentAct;
    private List<MaterialDto> lstAllMaterial;
    private List<MaterialDto> lstFilteredMaterial;
    private List<EmployeeDto> lstAllEmployees;
    private List<WarehouseDto> lstAllWarehouse;
    private List<MaterialGroupDto> lstMaterialGroup;
    private List<BuildingMaterialDto> lstBuildingMaterial;
    private BuildingProductDto buildingProductDto;


    private List<ProductGroupDto> lstProductGroup;
    private List<ProductSubgroupDto> lstAllProductSubgroup;
    private List<ProductSubgroupDto> lstProductSubgroup;
    private List<ProductDto> lstAllProduct;
    private List<ProductDto> lstProduct;
    private ProductDto selectedProduct;
    private ProductDto searchProduct;

    @PostConstruct
    public void init(){
        setCurrentAct(ControllerAction.State.VIEW);
        lstAllMaterial = materialService.findAllByImport();
        lstAllEmployees = employeeService.findAllActive();
        lstMaterialGroup = materialGroupService.findAllActive();
        lstAllWarehouse = warehouseService.findAllActiveWithStatus();
        lstBuildingMaterial = new ArrayList<>();
        buildingProductDto = new BuildingProductDto();
        buildingProductDto.setCreatedDate(DateUtil.getCurrentDayTS());
        buildingProductDto.setWarehouseName("<< Lựa chọn >>");
        lstProductGroup = productGroupService.findAllActive();
        lstAllProductSubgroup = productSubgroupService.findAllActive();
        if(lstProductGroup != null && !lstProductGroup.isEmpty()
                && lstAllProductSubgroup != null && !lstAllProductSubgroup.isEmpty()){
            lstProductSubgroup = new ArrayList<>();
            for(ProductSubgroupDto productSubgroupDto : lstAllProductSubgroup){
                if(productSubgroupDto.getProductGroupCode().equals(String.valueOf(lstProductGroup.get(0).getId()))){
                    lstProductSubgroup.add(productSubgroupDto);
                }
            }
        }
        lstAllProduct = productService.findAll();
        if(lstProductSubgroup != null && !lstProductSubgroup.isEmpty()
                && lstAllProduct != null && !lstAllProduct.isEmpty()){
            lstProduct = new ArrayList<>();
            for(ProductDto productDto : lstAllProduct){
                if(productDto.getProductSubgroupCode().equals(String.valueOf(lstProductSubgroup.get(0).getId()))){
                    lstProduct.add(productDto);
                }
            }
        }
        if(lstProduct != null && !lstProduct.isEmpty()){
            selectedProduct = lstProduct.get(0);
        }else {
            selectedProduct = new ProductDto();
        }
        searchProduct = new ProductDto();
    }

    public void selectProductGroup(){
        lstProductSubgroup = new ArrayList<>();
        for(ProductSubgroupDto productSubgroupDto : lstAllProductSubgroup){
            if(productSubgroupDto.getProductGroupCode().equals(String.valueOf(searchProduct.getProductGroupCode()))){
                lstProductSubgroup.add(productSubgroupDto);
            }
        }
        if(lstProductSubgroup != null && !lstProductSubgroup.isEmpty()){
            searchProduct.setProductSubgroupCode(lstProductSubgroup.get(0).getProductGroupCode());
            selectProductSubgroup();
        }
        PrimeFaces.current().ajax().update("main:txtProductSubgroup");
    }

    public void selectProductSubgroup(){
        lstProduct = new ArrayList<>();
        for(ProductDto productDto : lstAllProduct){
            if(productDto.getProductSubgroupCode().equals(String.valueOf(searchProduct.getProductSubgroupCode()))){
                lstProduct.add(productDto);
            }
        }
        if(lstProduct != null && !lstProduct.isEmpty()){
            searchProduct.setId(lstProduct.get(0).getId());
//            selectProduct();
        }
        PrimeFaces.current().ajax().update("main:tblProductList");
    }

    public void selectProduct(){
        if(selectedProduct != null) {
            saveProduct2Building();
            PrimeFaces.current().ajax().update("main:pnlSummary");
            PrimeFaces.current().executeScript("PF('dlgChooseProduct').hide()");
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR
                    , "Chưa lựa chọn thành phẩm !", "Có lỗi xảy ra"));
        }
    }

    public void selectWarehouse(int idx){
        buildingProductDto.setWarehouseId(lstAllWarehouse.get(idx).getId());
        buildingProductDto.setWarehouseName(lstAllWarehouse.get(idx).getName());
        PrimeFaces.current().executeScript("$('.dlgChooseWarehouse').modal('hide')");
        PrimeFaces.current().ajax().update("main:pnlSummary");
    }

    private void saveProduct2Building(){
        buildingProductDto.setProductId(selectedProduct.getId());
        buildingProductDto.setProductName(selectedProduct.getName());
        if(buildingProductDto.getBuildingMaterialDtos() != null) {
            for (BuildingMaterialDto buildingMaterialDto : buildingProductDto.getBuildingMaterialDtos()) {
                buildingMaterialDto.setProductId(selectedProduct.getId());
            }
        }
    }

    public void actSaveProduct(){
        if(selectedProduct == null || selectedProduct.getId() == null || selectedProduct.getId() == 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Có lỗi!"
                    , "Chưa chọn sản phẩm sẽ sản xuất"));
            return;
        }
        if(buildingProductDto.getBuildingMaterialDtos() == null
                || buildingProductDto.getBuildingMaterialDtos().isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Có lỗi!"
                    , "Bạn chưa chọn nguyên liệu sản xuất"));
            return;
        }
        try {
            buildingProductDto.setBuildingMaterialDtos(lstBuildingMaterial);
            saveProduct2Building();
            buildingService.save(buildingProductDto);
            init();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Thêm thành công!"
                    , null));
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Có lỗi!"
                    , e.getMessage()));
        }
    }

    public void actAddMaterial(int index){
        MaterialDto dto = lstAllMaterial.get(index);
        for(BuildingMaterialDto buildingMaterialDto : lstBuildingMaterial){
            if(buildingMaterialDto.getMaterialId().intValue() == dto.getId()){
                return;
            }
        }
        BuildingMaterialDto materialDto = new BuildingMaterialDto();
        materialDto.setMaterialName(dto.getName());
        materialDto.setMaterialId(dto.getId());
        materialDto.setMaterialCode(dto.getCode());
        materialDto.setPrice(dto.getCurrentPrice());
        materialDto.setQuantity(0);
        materialDto.setMaterialUnit(dto.getUnit());
        materialDto.setPrice(dto.getCurrentPrice());
        //Find relate import
        List<Integer> importIds = warehouseImportService.findAllActiveByMaterialId(dto.getId());
        materialDto.setImportId(importIds);
        lstBuildingMaterial.add(materialDto);
    }
    public void actDeleteMaterial(int index){
        lstBuildingMaterial.remove(index);
    }

    public void updatePrice(int index){
        BuildingMaterialDto materialDto = lstBuildingMaterial.get(index);
        materialDto.setTotal(materialDto.getPrice().longValue() * materialDto.getQuantity());
        updateBuildingProductInfo();
    }

    private void updateBuildingProductInfo(){
        Long totalPrice = 0l;
        for(BuildingMaterialDto materialDto : lstBuildingMaterial){
            if(materialDto.getTotal() != null) {
                totalPrice += materialDto.getTotal();
            }
        }
        buildingProductDto.setPrice(totalPrice);
    }

    public ControllerAction.State getCurrentAct() {
        return currentAct;
    }

    public void setCurrentAct(ControllerAction.State currentAct) {
        this.currentAct = currentAct;
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

    public List<WarehouseDto> getLstAllWarehouse() {
        return lstAllWarehouse;
    }

    public void setLstAllWarehouse(List<WarehouseDto> lstAllWarehouse) {
        this.lstAllWarehouse = lstAllWarehouse;
    }

    public List<BuildingMaterialDto> getLstBuildingMaterial() {
        return lstBuildingMaterial;
    }

    public void setLstBuildingMaterial(List<BuildingMaterialDto> lstBuildingMaterial) {
        this.lstBuildingMaterial = lstBuildingMaterial;
    }

    public BuildingProductDto getBuildingProductDto() {
        return buildingProductDto;
    }

    public void setBuildingProductDto(BuildingProductDto buildingProductDto) {
        this.buildingProductDto = buildingProductDto;
    }

    public List<ProductDto> getLstProduct() {
        return lstProduct;
    }

    public void setLstProduct(List<ProductDto> lstProduct) {
        this.lstProduct = lstProduct;
    }

    public ProductDto getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductDto selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<ProductSubgroupDto> getLstProductSubgroup() {
        return lstProductSubgroup;
    }

    public void setLstProductSubgroup(List<ProductSubgroupDto> lstProductSubgroup) {
        this.lstProductSubgroup = lstProductSubgroup;
    }

    public ProductDto getSearchProduct() {
        return searchProduct;
    }

    public void setSearchProduct(ProductDto searchProduct) {
        this.searchProduct = searchProduct;
    }

    public List<ProductGroupDto> getLstProductGroup() {
        return lstProductGroup;
    }

    public void setLstProductGroup(List<ProductGroupDto> lstProductGroup) {
        this.lstProductGroup = lstProductGroup;
    }
}
