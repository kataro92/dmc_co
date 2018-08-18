package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.model.MaterialDto;

import java.util.List;

public interface MaterialService {
    List<MaterialDto> findAll();
    List<MaterialDto> findAllActive();
    void save(MaterialDto userDto);
    void delete(Integer id);
    List<MaterialDto> findBySubgroupId(int id);
    MaterialDto findByCode(String code);
    List<MaterialDto> findAllByImport();
    MaterialDto findByImport(int materialId, int importId);
}
