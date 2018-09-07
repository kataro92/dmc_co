package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.dto.MaterialDismissDetailDto;
import com.kat.dmc.common.dto.MaterialDismissDto;
import com.kat.dmc.common.dto.MaterialIETDDto;

import java.util.List;

public interface WarehouseDismissService {
    List<MaterialDismissDto> findAll();
    List<MaterialDismissDto> findAllActive();
    List<MaterialIETDDto> findAllActiveByWarehouseId(Integer id);
    List<MaterialDismissDetailDto> findAllActiveDtlByMaterialImpId(int materialDismiss);
    void save(MaterialDismissDto userDto);
    void delete(Integer id);
}
