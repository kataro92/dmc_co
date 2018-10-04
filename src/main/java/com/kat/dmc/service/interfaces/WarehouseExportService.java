package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.dto.MaterialExportDetailDto;
import com.kat.dmc.common.dto.MaterialExportDto;
import com.kat.dmc.common.dto.MaterialIETDDto;

import java.util.List;

public interface WarehouseExportService {
    List<MaterialExportDto> findAll();
    List<MaterialExportDto> findAllActive();
    List<MaterialIETDDto> findAllActiveByWarehouseId(Integer id);
    List<MaterialExportDetailDto> findAllActiveDtlByMaterialImpId(int materialExport);
    void save(MaterialExportDto userDto);
    void delete(Integer id);
}
