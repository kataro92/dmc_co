package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.dto.ConfigDto;

import java.util.List;

public interface ConfigService {
    List<ConfigDto> findAll();
    void save(ConfigDto userDto);
    void delete(Integer id);
}
