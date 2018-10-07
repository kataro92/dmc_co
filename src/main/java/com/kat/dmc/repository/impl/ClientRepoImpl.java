package com.kat.dmc.repository.impl;

import com.kat.dmc.common.dto.ClientDto;
import com.kat.dmc.common.model.DmcClientEntity;
import com.kat.dmc.common.model.DmcClientEntity_;
import com.kat.dmc.repository.interfaces.ClientRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ClientRepoImpl implements ClientRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ClientDto> findAll(){
        String strSQL = "SELECT client.address, client.code, client.contact_person, " +
                "client.def_code, client.email, employee.code employee_code, " +
                "client.fax, client.name, client.phone, client.status, client.tax_code, " +
                "client.trademark, client.id, employee.id emp_id, department.id dept_id, " +
                "employee.name emp_name, department.name deptName " +
                " FROM dmc_client client LEFT JOIN dmc_employee employee ON client.emp_id = employee.id  " +
                " LEFT JOIN dmc_department department ON client.dept_id = department.id";

        Query query = entityManager.createNativeQuery(strSQL);
        List<Object[]> resultList = query.getResultList();
        List<ClientDto> clientDtos = new ArrayList<>();
        for(Object[] objects : resultList){
            clientDtos.add(object2Dto(objects));
        }
        return clientDtos;
    }

    private ClientDto object2Dto(Object[] objects){
        ClientDto clientDto = new ClientDto();
        clientDto.setAddress((String) objects[0]);
        clientDto.setCode((String) objects[1]);
        clientDto.setContactPerson((String) objects[2]);
        clientDto.setDefCode((String) objects[3]);
        clientDto.setEmail((String) objects[4]);
        clientDto.setEmployeeCode((String) objects[5]);
        clientDto.setFax((String) objects[6]);
        clientDto.setName((String) objects[7]);
        clientDto.setPhone((String) objects[8]);
        clientDto.setStatus((Integer) objects[9]);
        clientDto.setTaxCode((String) objects[10]);
        clientDto.setTrademark((String) objects[11]);
        clientDto.setId((Integer) objects[12]);
        clientDto.setEmpId((Integer) objects[13]);
        clientDto.setDeptId((Integer) objects[14]);
        clientDto.setEmpName((String) objects[15]);
        clientDto.setDeptName((String) objects[16]);
        return clientDto;
    }

    @Override
    public void save(DmcClientEntity dmcClientEntity) {
        entityManager.merge(dmcClientEntity);
    }

    @Override
    public void delete(DmcClientEntity dmcClientEntity) {
        entityManager.remove(dmcClientEntity);
    }

    @Override
    public DmcClientEntity findById(Integer clientId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcClientEntity> criteriaQuery = builder.createQuery(DmcClientEntity.class);
        Root<DmcClientEntity> root = criteriaQuery.from(DmcClientEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcClientEntity_.id), clientId));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcClientEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            Logger.getLogger(this.getClass().getName()).warning(ex.getMessage());
            return null;
        }
    }
}
