package com.kat.dmc.service.impl;

import com.kat.dmc.common.model.DepartmentDto;
import com.kat.dmc.common.model.DepartmentEntity;
import com.kat.dmc.common.model.EmployeeDto;
import com.kat.dmc.common.util.CommonUtil;
import com.kat.dmc.repository.interfaces.DeptRepo;
import com.kat.dmc.service.interfaces.DeptService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptRepo deptRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<DepartmentDto> findAll() {
        List<DepartmentEntity> entityList = deptRepo.findAll();
        List<DepartmentDto> lstReturn = new ArrayList<>();
        for(DepartmentEntity entity : entityList){
            if(CommonUtil.isNull(entity.getParentCode())){
                lstReturn.add(modelMapper.map(entity, DepartmentDto.class));
            }
        }
        for(DepartmentDto departmentDto : lstReturn){
            rescusiveMapDepartment(departmentDto, entityList);
        }
        return lstReturn;
    }

    private void rescusiveMapDepartment(DepartmentDto departmentDto, List<DepartmentEntity> entityList) {
        departmentDto.setLstChildDept(new ArrayList<>());
        for(DepartmentEntity entity : entityList){
            if(entity.getParentCode() != null
                    && entity.getParentCode().equals(String.valueOf(departmentDto.getId()))){
                DepartmentDto dto = modelMapper.map(entity, DepartmentDto.class);
                rescusiveMapDepartment(dto, entityList);
                departmentDto.getLstChildDept().add(dto);
            }
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void save(DepartmentDto selectedDept) {

    }

    @Override
    public List<EmployeeDto> findEmpByDeptId() {
        return null;
    }
}
