package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcWarehouseEntity;
import com.kat.dmc.common.model.DmcWarehouseStatus;
import com.kat.dmc.common.req.WarehouseSearchReq;

import java.util.List;

public interface WarehouseRepo {
    List<DmcWarehouseEntity> findAll();
    List<DmcWarehouseEntity> findAllActive();
    void save(DmcWarehouseEntity userEntity);
    void delete(DmcWarehouseEntity userEntity);
    DmcWarehouseEntity findById(Integer id);
    List<DmcWarehouseEntity> findAllActiveByPermission(Boolean canImport, Boolean canExport, Boolean canTransfer, Boolean canDismiss);
    List<DmcWarehouseStatus> findAllBySearchReq(WarehouseSearchReq sumOnStockReq);
    List<DmcWarehouseStatus> findImportBySearchReq(WarehouseSearchReq sumOnStockReq);
    List<DmcWarehouseStatus> findExportBySearchReq(WarehouseSearchReq sumOnStockReq);
    List<DmcWarehouseStatus> findTempImportBySearchReq(WarehouseSearchReq sumOnStockReq);
    List<DmcWarehouseStatus> findDailyStatus(Integer warehouseId);
}
