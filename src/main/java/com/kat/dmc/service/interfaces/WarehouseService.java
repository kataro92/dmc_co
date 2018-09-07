package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.dto.MaterialIETDDto;
import com.kat.dmc.common.dto.MaterialOnStockDto;
import com.kat.dmc.common.dto.WarehouseDto;
import com.kat.dmc.common.req.WarehouseSearchReq;

import java.util.List;

public interface WarehouseService {
    List<MaterialOnStockDto> findAllMaterialOnStock();
    List<WarehouseDto> findAll();
    List<WarehouseDto> findAllActive();
    List<WarehouseDto> findAllActiveWithStatus();
    List<WarehouseDto> findAllActiveByPermission(Boolean canImport, Boolean canExport, Boolean canTransfer, Boolean canDismiss);
    void save(WarehouseDto userDto);
    void delete(Integer id);
    List<MaterialIETDDto> findAllBySearchReq(WarehouseSearchReq sumOnStockReq);
}
