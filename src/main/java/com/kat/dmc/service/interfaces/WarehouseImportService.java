package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.model.MaterialIETDDto;
import com.kat.dmc.common.model.MaterialImportDetailDto;
import com.kat.dmc.common.model.MaterialImportDto;

import java.util.List;

public interface WarehouseImportService {
    List<MaterialImportDto> findAll();
    List<MaterialImportDto> findAllActive();
    List<MaterialIETDDto> findAllActiveByWarehouseId(Integer id);
    List<MaterialImportDetailDto> findAllActiveDtlByMaterialImpId(int materialImport);
    void save(MaterialImportDto userDto);
    void delete(Integer id);
}
