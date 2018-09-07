package com.kat.dmc.service.impl;

import com.kat.dmc.common.dto.ClientDto;
import com.kat.dmc.common.model.ClientEntity;
import com.kat.dmc.repository.interfaces.ClientRepo;
import com.kat.dmc.service.interfaces.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepo clientRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ClientDto> findAll() {
        return clientRepo.findAll();
    }

    @Override
    public void save(ClientDto clientDto) {
        ClientEntity savingObj = clientRepo.findById(clientDto.getId());
        modelMapper.map(clientDto, savingObj);
        clientRepo.save(savingObj);
    }

    @Override
    public void delete(Integer clientId) {
        ClientEntity deletingObj = clientRepo.findById(clientId);
        clientRepo.delete(deletingObj);
    }
}
