package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.model.MaterialIETDDto;
import com.kat.dmc.common.model.MaterialExportDetailDto;
import com.kat.dmc.common.model.MaterialExportDto;

import java.util.List;

public interface WarehouseExportService {
    List<MaterialExportDto> findAll();
    List<MaterialExportDto> findAllActive();
    List<MaterialIETDDto> findAllActiveByWarehouseId(Integer id);
    List<MaterialExportDetailDto> findAllActiveDtlByMaterialImpId(int materialExport);
    void save(MaterialExportDto userDto);
    void delete(Integer id);
}
