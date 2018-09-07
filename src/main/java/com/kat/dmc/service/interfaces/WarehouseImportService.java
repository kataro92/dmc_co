package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.dto.MaterialIETDDto;
import com.kat.dmc.common.dto.MaterialImportDetailDto;
import com.kat.dmc.common.dto.MaterialImportDto;

import java.util.List;

public interface WarehouseImportService {
    List<MaterialImportDto> findAll();
    List<MaterialImportDto> findAllActive();
    List<MaterialImportDto> findAllActiveQuantity();
    List<MaterialIETDDto> findAllActiveByWarehouseId(Integer id);
    List<MaterialImportDetailDto> findAllActiveDtlByMaterialImpId(int materialImport);
    List<MaterialImportDto> findMaterialAvaliabeByIdAndQuantity(Integer materialId, Integer quantity);
    void save(MaterialImportDto userDto);
    void delete(Integer id);
    List<Integer> findAllActiveByMaterialId(int id);
}
