package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.dto.MaterialGroupDto;

import java.util.List;

public interface MaterialGroupService {
    List<MaterialGroupDto> findAll();
    List<MaterialGroupDto> findAllActive();
    void save(MaterialGroupDto userDto);
    void delete(Integer id);
}
