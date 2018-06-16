package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.model.*;
import com.kat.dmc.common.util.SQLErrorUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.*;
import org.modelmapper.ModelMapper;
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

@Named("productCreator")
@ConversationScoped
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

    private ControllerAction.State currentAct;
    private List<MaterialDto> lstAllMaterial;
    private List<MaterialDto> lstFilteredMaterial;
    private List<EmployeeDto> lstAllEmployees;
    private List<WarehouseDto> lstAllWarehouse;
    private List<MaterialGroupDto> lstMaterialGroup;
    private List<BuildingMaterialDto> lstBuildingMaterial;
    private BuildingProductDto buildingProductDto;

    @PostConstruct
    public void init(){
        setCurrentAct(ControllerAction.State.VIEW);
        lstAllMaterial = materialService.findAllByImport();
        lstAllEmployees = employeeService.findAllActive();
        lstMaterialGroup = materialGroupService.findAllActive();
        lstAllWarehouse = warehouseService.findAllActive();
        lstBuildingMaterial = new ArrayList<>();
        buildingProductDto = new BuildingProductDto();
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

        lstBuildingMaterial.add(materialDto);
    }
    public void actDeleteMaterial(int index){
        lstBuildingMaterial.remove(index);
    }

    public void updatePrice(int index){
        BuildingMaterialDto materialDto = lstBuildingMaterial.get(index);
        materialDto.setTotal(materialDto.getPrice().longValue() * materialDto.getQuantity());
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
}
