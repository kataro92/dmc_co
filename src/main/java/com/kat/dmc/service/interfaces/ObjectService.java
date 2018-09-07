package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.dto.ObjectDto;

import java.util.List;

public interface ObjectService {
    List<ObjectDto> findAll();
    void save(ObjectDto userDto);
    void delete(Integer userId);
    void saveNewPermission(List<ObjectDto> lstNewPermission, Integer userId);
    List<Integer> findObjectIdByUserId(Integer userId);
}
