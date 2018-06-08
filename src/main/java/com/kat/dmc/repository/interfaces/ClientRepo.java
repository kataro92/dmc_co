package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.ClientDto;
import com.kat.dmc.common.model.ClientEntity;

import java.util.List;

public interface ClientRepo {
    List<ClientDto> findAll();
    void save(ClientEntity clientEntity);
    void delete(ClientEntity clientEntity);
    ClientEntity findById(Integer clientId);
}
