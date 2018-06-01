package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.EmployeeEntity;
import com.kat.dmc.common.model.EmployeeEntity;
import com.kat.dmc.common.model.EmployeeEntity_;
import com.kat.dmc.repository.interfaces.EmployeeRepo;
import com.kat.dmc.repository.interfaces.UtilRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepoImpl implements EmployeeRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;

    @Override
    public List<EmployeeEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EmployeeEntity> criteriaQuery = builder.createQuery(EmployeeEntity.class);
        Root<EmployeeEntity> root = criteriaQuery.from(EmployeeEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(EmployeeEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<EmployeeEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<EmployeeEntity> findByDeptId(int deptId) {
        return null;
    }

    @Override
    public void save(EmployeeEntity userEntity) {

    }

    @Override
    public void delete(EmployeeEntity userEntity) {

    }

    @Override
    public EmployeeEntity findById(Integer userId) {
        return null;
    }
}