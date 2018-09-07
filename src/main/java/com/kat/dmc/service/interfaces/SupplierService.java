package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.dto.SupplierDto;

import java.util.List;

public interface SupplierService {
    List<SupplierDto> findAll();
    List<SupplierDto> findAllActive();
    void save(SupplierDto userDto);
    void delete(Integer id);
}
