package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.model.ClientDto;

import java.util.List;

public interface ClientService {
    List<ClientDto> findAll();
    void save(ClientDto userDto);
    void delete(Integer userId);
}
