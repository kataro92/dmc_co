package com.kat.dmc.service.impl;

import com.kat.dmc.common.dto.EmployeeDto;
import com.kat.dmc.common.model.DmcEmployeeEntity;
import com.kat.dmc.common.util.CommonUtil;
import com.kat.dmc.common.util.PasswordUtil;
import com.kat.dmc.repository.interfaces.EmployeeRepo;
import com.kat.dmc.service.interfaces.DocumentService;
import com.kat.dmc.service.interfaces.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    DocumentService documentService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<EmployeeDto> findByDeptId(int id) {
        List<DmcEmployeeEntity> byDeptId = employeeRepo.findByDeptId(id);
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        if(byDeptId == null || byDeptId.isEmpty()){
            return employeeDtos;
        }
        for (DmcEmployeeEntity entity : byDeptId) {
            EmployeeDto employeeDto = modelMapper.map(entity, EmployeeDto.class);
            employeeDto.setLstDocuments(documentService.findByEmployeeId(employeeDto.getId()));
            employeeDtos.add(employeeDto);
        }
        return employeeDtos;
    }

    @Override
    public List<EmployeeDto> findAllActive() {
        return employeeRepo.findAllActive();
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepo.findAll().stream().map(this::TsEmployee2EmployeeDto).collect(Collectors.toList());
    }

    @Override
    public void save(EmployeeDto employeeDto) {
        DmcEmployeeEntity savingObj = employeeRepo.findById(employeeDto.getId());
        if(savingObj == null){
            savingObj = new DmcEmployeeEntity();
            savingObj.setId(employeeDto.getId());
        }
        savingObj.setName(employeeDto.getName());
        savingObj.setUserType(employeeDto.getUserType());
        savingObj.setCode(employeeDto.getCode());
        savingObj.setStatus(employeeDto.getStatus());
        if(!CommonUtil.isEmpty(employeeDto.getPassword())){
            savingObj.setPassword(PasswordUtil.hashMD5(employeeDto.getPassword()));
        }
        employeeRepo.save(savingObj);
    }

    @Override
    public void delete(Integer employeeId) {
        DmcEmployeeEntity deletingObj = employeeRepo.findById(employeeId);
        employeeRepo.delete(deletingObj);
    }

    private EmployeeDto TsEmployee2EmployeeDto(DmcEmployeeEntity input){
        EmployeeDto map = modelMapper.map(input, EmployeeDto.class);
        map.setStatus(input.getStatus());
        map.setFullName(input.getName());
        map.setUserType(input.getUserType());
        return map;
    }
}
