package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.model.BuildingProductDto;
import com.kat.dmc.common.model.DepartmentDto;
import com.kat.dmc.common.model.EmployeeDto;
import com.kat.dmc.common.model.MaterialImportDetailDto;

import java.util.List;

public interface BuildingService {
    List<BuildingProductDto> findAll();
    void delete(int buildingProductId);
    void save(BuildingProductDto buildingProductDto) throws Exception;
    List<BuildingProductDto> findProductByWarehouse(int buildingProductId);
    List<MaterialImportDetailDto> findAllActiveDtlByMaterialImpId(int importId);
}
