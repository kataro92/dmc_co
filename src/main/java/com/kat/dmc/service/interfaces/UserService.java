package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    void save(UserDto userDto);
    void delete(Integer userId);
}
