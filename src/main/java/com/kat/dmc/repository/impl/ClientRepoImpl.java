package com.kat.dmc.repository.impl;

import com.kat.dmc.common.dto.ClientDto;
import com.kat.dmc.common.model.ClientEntity;
import com.kat.dmc.common.model.ClientEntity_;
import com.kat.dmc.repository.interfaces.ClientRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ClientRepoImpl implements ClientRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ClientDto> findAll(){
        String strSQL = "SELECT client.address, client.code, client.contact_person, " +
                "client.def_code, client.email, employee.code employee_code, " +
                "client.fax, client.name, client.phone, client.status, client.tax_code, " +
                "client.trademark, client._id, employee._id emp_id, department._id dept_id, " +
                "employee.name emp_name, department.name deptName " +
                " FROM client LEFT JOIN employee ON client.emp_id = employee._id  " +
                " LEFT JOIN department ON client.dept_id = department._id";

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
    public void save(ClientEntity clientEntity) {
        entityManager.merge(clientEntity);
    }

    @Override
    public void delete(ClientEntity clientEntity) {
        entityManager.remove(clientEntity);
    }

    @Override
    public ClientEntity findById(Integer clientId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ClientEntity> criteriaQuery = builder.createQuery(ClientEntity.class);
        Root<ClientEntity> root = criteriaQuery.from(ClientEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(ClientEntity_.id), clientId));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<ClientEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
