package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.model.MaterialDismissDetailDto;
import com.kat.dmc.common.model.MaterialDismissDto;
import com.kat.dmc.common.model.MaterialIETDDto;

import java.util.List;

public interface WarehouseDismissService {
    List<MaterialDismissDto> findAll();
    List<MaterialDismissDto> findAllActive();
    List<MaterialIETDDto> findAllActiveByWarehouseId(Integer id);
    List<MaterialDismissDetailDto> findAllActiveDtlByMaterialImpId(int materialDismiss);
    void save(MaterialDismissDto userDto);
    void delete(Integer id);
}
