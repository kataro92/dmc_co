package com.kat.dmc.service.impl;

import com.kat.dmc.common.model.*;
import com.kat.dmc.common.util.CommonUtil;
import com.kat.dmc.repository.interfaces.DeptRepo;
import com.kat.dmc.repository.interfaces.DocumentRepo;
import com.kat.dmc.repository.interfaces.EmployeeRepo;
import com.kat.dmc.service.interfaces.DeptService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptRepo deptRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    DocumentRepo documentRepo;

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
        deptRepo.delete(deptRepo.findById(id));
    }

    @Override
    public void save(DepartmentDto selectedDept) {
        DepartmentEntity savingObj = modelMapper.map(selectedDept, DepartmentEntity.class);
        savingObj.setCreatedDate(new Timestamp(new Date().getTime()));
        deptRepo.save(savingObj);
        List<EmployeeDto> lstEmployees = selectedDept.getLstEmployees();
        List<Integer> lstEmpIds = new ArrayList<>();
        if(lstEmployees != null && !lstEmployees.isEmpty()){
            for(EmployeeDto employeeDto : lstEmployees){
                EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
                employeeEntity.setDefCode(savingObj.getDefCode());
                employeeEntity.setDeptId(savingObj.getId());
                employeeRepo.save(employeeEntity);
                lstEmpIds.add(employeeEntity.getId());
                List<DocumentDto> lstDocuments = employeeDto.getLstDocuments();
                List<Integer> lstDocIds = new ArrayList<>();
                if(lstDocuments != null && !lstDocuments.isEmpty()){
                    for(DocumentDto documentDto : lstDocuments){
                        DmcDocumentEntity documentEntity = modelMapper.map(documentDto, DmcDocumentEntity.class);
                        documentEntity.setType("document");
                        documentEntity.setParentId(employeeDto.getId());
                        documentRepo.save(documentEntity);
                        lstDocIds.add(documentEntity.getId());
                    }
                }
                documentRepo.deleteByEmpIdNotIn(lstDocIds, employeeEntity.getId());
            }
        }
        employeeRepo.deleteByDeptIdNotIn(lstEmpIds, savingObj.getId());
    }

    @Override
    public List<EmployeeDto> findEmpByDeptId(int deptId) {
        List<EmployeeEntity> employeeEntities = employeeRepo.findByDeptId(deptId);
        List<EmployeeDto> returnEmployees = new ArrayList<>();
        employeeEntities.forEach (entity -> {
            returnEmployees.add(modelMapper.map(entity, EmployeeDto.class));
        });
        return  returnEmployees;
    }
}
