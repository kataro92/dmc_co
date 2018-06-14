package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.model.MaterialTransferDetailDto;
import com.kat.dmc.common.model.MaterialTransferDto;
import com.kat.dmc.common.model.MaterialIETDDto;

import java.util.List;

public interface WarehouseTransferService {
    List<MaterialTransferDto> findAll();
    List<MaterialTransferDto> findAllActive();
    List<MaterialIETDDto> findAllActiveByWarehouseId(Integer id);
    List<MaterialTransferDetailDto> findAllActiveDtlByMaterialImpId(int materialTransfer);
    void save(MaterialTransferDto userDto);
    void delete(Integer id);
}
