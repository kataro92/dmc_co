package com.kat.dmc.repository.impl;

import com.kat.dmc.common.dto.EmployeeDto;
import com.kat.dmc.common.model.DmcEmployeeEntity;
import com.kat.dmc.common.model.DmcEmployeeEntity_;
import com.kat.dmc.repository.interfaces.EmployeeRepo;
import com.kat.dmc.repository.interfaces.UtilRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.kat.dmc.common.constant.CommonConst.Code.DEFAULT_ACTIVE;

@Repository
public class EmployeeRepoImpl implements EmployeeRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;


    @Override
    public DmcEmployeeEntity getEmployeeByEmployeenameAndPassword(String username, String md5_password) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcEmployeeEntity> criteriaQuery = builder.createQuery(DmcEmployeeEntity.class);
        Root<DmcEmployeeEntity> root = criteriaQuery.from(DmcEmployeeEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcEmployeeEntity_.id)));
        predicates.add(builder.equal(root.get(DmcEmployeeEntity_.username), username));
        predicates.add(builder.equal(root.get(DmcEmployeeEntity_.password), md5_password));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcEmployeeEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            Logger.getLogger(this.getClass().getName()).warning(ex.getMessage());
            return null;
        }
    }
    
    @Override
    public List<DmcEmployeeEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcEmployeeEntity> criteriaQuery = builder.createQuery(DmcEmployeeEntity.class);
        Root<DmcEmployeeEntity> root = criteriaQuery.from(DmcEmployeeEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcEmployeeEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcEmployeeEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcEmployeeEntity> findByDeptId(int deptId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcEmployeeEntity> criteriaQuery = builder.createQuery(DmcEmployeeEntity.class);
        Root<DmcEmployeeEntity> root = criteriaQuery.from(DmcEmployeeEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcEmployeeEntity_.deptId), deptId));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcEmployeeEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcEmployeeEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcEmployeeEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcEmployeeEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcEmployeeEntity> criteriaQuery = builder.createQuery(DmcEmployeeEntity.class);
        Root<DmcEmployeeEntity> root = criteriaQuery.from(DmcEmployeeEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcEmployeeEntity_.id),id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcEmployeeEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            Logger.getLogger(this.getClass().getName()).warning(ex.getMessage());
            return null;
        }
    }

    @Override
    public void deleteByDeptIdNotIn(List<Integer> lstEmpIds, Integer deptId) {
        String lstEmp = "-1";
        for(Integer id : lstEmpIds){
            lstEmp += ","+id;
        }
        Query query = entityManager.createNativeQuery("DELETE FROM dmc_employee WHERE dept_id = ?1 AND id NOT IN (" +
                lstEmp +
                ")")
                .setParameter("1", deptId);
        query.executeUpdate();
    }

    @Override
    public List<EmployeeDto> findAllActive() {
        String strSQL = "SELECT employee.address, employee.code emp_code, employee.date_of_birth, " +
                "employee.def_code, employee.email, employee.first_name, " +
                " employee.gender, employee.identity_card_card_number, " +
                " employee.identity_card_issued_by, employee.identity_card_issued_date, " +
                " employee.job_position_code, employee.leave_date, employee.name emp_name, " +
                " employee.phone, employee.start_date, employee.status emp_status, employee.user_code, " +
                "employee.id emp_id, employee.dept_id, department.name dept_name " +
                " FROM dmc_employee employee JOIN dmc_department department ON employee.dept_id = department.id" +
                " WHERE employee.status = " + DEFAULT_ACTIVE.code() +
                " ORDER BY employee.id";
        Query query = entityManager.createNativeQuery(strSQL);
        List<Object[]> lstResult = query.getResultList();
        List<EmployeeDto> lstReturn = new ArrayList<>();
        for(Object[] objects : lstResult){
            lstReturn.add(object2Dto(objects));
        }
        return lstReturn;
    }

    private EmployeeDto object2Dto(Object[] objects) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setAddress((String) objects[0]);
        employeeDto.setCode((String) objects[1]);
        employeeDto.setDateOfBirth((Timestamp) objects[2]);
        employeeDto.setDefCode((String) objects[3]);
        employeeDto.setEmail((String) objects[4]);
        employeeDto.setFirstName((String) objects[5]);
        employeeDto.setGender((String) objects[6]);
        employeeDto.setIdentityCardCardNumber((String) objects[7]);
        employeeDto.setIdentityCardIssuedBy((String) objects[8]);
        employeeDto.setIdentityCardIssuedDate((Timestamp) objects[9]);
        employeeDto.setJobPositionCode((String) objects[10]);
        employeeDto.setLeaveDate((String) objects[11]);
        employeeDto.setName((String) objects[12]);
        employeeDto.setPhone((String) objects[13]);
        employeeDto.setStartDate((String) objects[14]);
        employeeDto.setStatus((Integer) objects[15]);
        employeeDto.setUserCode((String) objects[16]);
        employeeDto.setId((Integer) objects[17]);
        employeeDto.setDeptId((Integer) objects[18]);
        employeeDto.setDeptName((String) objects[19]);
        return employeeDto;
    }
}
