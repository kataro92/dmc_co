package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.model.WarehouseDto;

import java.util.List;

public interface WarehouseService {
    List<WarehouseDto> findAll();
    List<WarehouseDto> findAllActive();
    List<WarehouseDto> findAllActiveByPermission(Boolean canImport, Boolean canExport, Boolean canTransfer, Boolean canDismiss);
    void save(WarehouseDto userDto);
    void delete(Integer id);
}
