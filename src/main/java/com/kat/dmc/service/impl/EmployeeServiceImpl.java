package com.kat.dmc.service.impl;

import com.kat.dmc.common.model.EmployeeDto;
import com.kat.dmc.common.model.EmployeeEntity;
import com.kat.dmc.repository.interfaces.EmployeeRepo;
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
    ModelMapper modelMapper;

    @Override
    public List<EmployeeDto> findByDeptId(int id) {
        List<EmployeeEntity> byDeptId = employeeRepo.findByDeptId(id);
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        byDeptId.forEach(entity -> employeeDtos.add(modelMapper.map(entity, EmployeeDto.class)));
        return employeeDtos;
    }
}
