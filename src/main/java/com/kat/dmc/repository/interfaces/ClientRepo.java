package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.dto.ClientDto;
import com.kat.dmc.common.model.DmcClientEntity;

import java.util.List;

public interface ClientRepo {
    List<ClientDto> findAll();
    void save(DmcClientEntity dmcClientEntity);
    void delete(DmcClientEntity dmcClientEntity);
    DmcClientEntity findById(Integer clientId);
}
