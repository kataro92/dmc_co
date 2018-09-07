package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.dto.ClientDto;

import java.util.List;

public interface ClientService {
    List<ClientDto> findAll();
    void save(ClientDto userDto);
    void delete(Integer userId);
}
