package com.kat.dmc.service.impl;

import com.kat.dmc.common.dto.EmployeeDto;
import com.kat.dmc.common.model.EmployeeEntity;
import com.kat.dmc.repository.interfaces.EmployeeRepo;
import com.kat.dmc.service.interfaces.DocumentService;
import com.kat.dmc.service.interfaces.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<EmployeeEntity> byDeptId = employeeRepo.findByDeptId(id);
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        if(byDeptId == null || byDeptId.isEmpty()){
            return employeeDtos;
        }
        for (EmployeeEntity entity : byDeptId) {
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
}
