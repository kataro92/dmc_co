package com.kat.dmc.service.impl;

import com.kat.dmc.common.model.SupplierDto;
import com.kat.dmc.common.model.SupplierEntity;
import com.kat.dmc.repository.interfaces.SupplierRepo;
import com.kat.dmc.service.interfaces.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    SupplierRepo supplierRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<SupplierDto> findAll() {
        return supplierRepo.findAll().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public List<SupplierDto> findAllActive() {
        return supplierRepo.findAllActive().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public void save(SupplierDto userDto) {
        SupplierEntity savingObj = modelMapper.map(userDto, SupplierEntity.class);
        supplierRepo.save(savingObj);
    }

    @Override
    public void delete(Integer id) {
        supplierRepo.delete(supplierRepo.findById(id));
    }

    private SupplierDto entity2Dto(SupplierEntity entity){
        return modelMapper.map(entity, SupplierDto.class);
    }
}
