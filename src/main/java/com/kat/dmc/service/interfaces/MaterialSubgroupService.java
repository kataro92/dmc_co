package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.model.EmployeeDto;
import com.kat.dmc.common.model.MaterialSubgroupDto;

import java.util.List;

public interface MaterialSubgroupService {
    List<MaterialSubgroupDto> findAll();
    List<MaterialSubgroupDto> findAllActive();
    void save(MaterialSubgroupDto userDto);
    void delete(Integer id);
    List<MaterialSubgroupDto> findAllByGroupId(int id);
}
