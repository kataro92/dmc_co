package com.kat.dmc.service.impl;

import com.kat.dmc.common.dto.DepartmentDto;
import com.kat.dmc.common.dto.DocumentDto;
import com.kat.dmc.common.dto.EmployeeDto;
import com.kat.dmc.common.model.DmcDepartmentEntity;
import com.kat.dmc.common.model.DmcDocumentEntity;
import com.kat.dmc.common.model.DmcEmployeeEntity;
import com.kat.dmc.common.util.CommonUtil;
import com.kat.dmc.repository.interfaces.DeptRepo;
import com.kat.dmc.repository.interfaces.DocumentRepo;
import com.kat.dmc.repository.interfaces.EmployeeRepo;
import com.kat.dmc.service.interfaces.DeptService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
        List<DmcDepartmentEntity> entityList = deptRepo.findAll();
        List<DepartmentDto> lstReturn = new ArrayList<>();
        for(DmcDepartmentEntity entity : entityList){
            if(CommonUtil.isEmpty(entity.getParentCode())){
                lstReturn.add(modelMapper.map(entity, DepartmentDto.class));
            }
        }
        for(DepartmentDto departmentDto : lstReturn){
            rescusiveMapDepartment(departmentDto, entityList);
        }
        return lstReturn;
    }

    private void rescusiveMapDepartment(DepartmentDto departmentDto, List<DmcDepartmentEntity> entityList) {
        departmentDto.setLstChildDept(new ArrayList<>());
        for(DmcDepartmentEntity entity : entityList){
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
        DmcDepartmentEntity savingObj = modelMapper.map(selectedDept, DmcDepartmentEntity.class);
        deptRepo.save(savingObj);
        List<EmployeeDto> lstEmployees = selectedDept.getLstEmployees();
        List<Integer> lstEmpIds = new ArrayList<>();
        if(lstEmployees != null && !lstEmployees.isEmpty()){
            for(EmployeeDto employeeDto : lstEmployees){
                DmcEmployeeEntity dmcEmployeeEntity = modelMapper.map(employeeDto, DmcEmployeeEntity.class);
                dmcEmployeeEntity.setDefCode(savingObj.getDefCode());
                dmcEmployeeEntity.setDeptId(savingObj.getId());
                employeeRepo.save(dmcEmployeeEntity);
                lstEmpIds.add(dmcEmployeeEntity.getId());
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
                documentRepo.deleteByEmpIdNotIn(lstDocIds, dmcEmployeeEntity.getId());
            }
        }
        employeeRepo.deleteByDeptIdNotIn(lstEmpIds, savingObj.getId());
    }

    @Override
    public List<EmployeeDto> findEmpByDeptId(int deptId) {
        List<DmcEmployeeEntity> employeeEntities = employeeRepo.findByDeptId(deptId);
        List<EmployeeDto> returnEmployees = new ArrayList<>();
        employeeEntities.forEach (entity -> returnEmployees.add(modelMapper.map(entity, EmployeeDto.class)));
        return  returnEmployees;
    }

    @Override
    public List<DepartmentDto> findByReq(DepartmentDto searchDept) {
        List<DmcDepartmentEntity> entityLists = deptRepo.findByReq(searchDept);
        List<DepartmentDto> lstReturn = new ArrayList<>();
        for(DmcDepartmentEntity entity : entityLists){
            if(CommonUtil.isEmpty(entity.getParentCode())){
                lstReturn.add(modelMapper.map(entity, DepartmentDto.class));
            }
        }
        if(lstReturn.isEmpty() && !entityLists.isEmpty()){
            boolean isRoot = true;
            for(DmcDepartmentEntity parentEntity : entityLists) {
                for (DmcDepartmentEntity entity : entityLists) {
                    if (entity.getId() == Integer.parseInt(parentEntity.getParentCode())) {
                        isRoot = false;
                    }
                }
                if(isRoot){
                    lstReturn.add(modelMapper.map(parentEntity, DepartmentDto.class));
                }
            }
        }
        for(DepartmentDto departmentDto : lstReturn){
            rescusiveMapDepartment(departmentDto, entityLists);
        }
        return lstReturn;
    }
}
