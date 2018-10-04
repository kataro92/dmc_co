package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.dto.WarehouseStockDto;
import com.kat.dmc.common.model.DmcWarehouseStockEntity;

import java.util.Date;
import java.util.List;

public interface WarehouseStockRepo {
    List<DmcWarehouseStockEntity> findAll();
    List<WarehouseStockDto> findImportStock(Date checkingDate);
    List<WarehouseStockDto> findExportStock(Date checkingDate);
    List<WarehouseStockDto> findTransferStock(Date checkingDate);
    List<WarehouseStockDto> findDismissStock(Date checkingDate);
    void save(DmcWarehouseStockEntity userEntity);
    void delete(DmcWarehouseStockEntity userEntity);
    DmcWarehouseStockEntity findById(Integer id);
    void deleteStock(Date checkingDate, String type);
}
