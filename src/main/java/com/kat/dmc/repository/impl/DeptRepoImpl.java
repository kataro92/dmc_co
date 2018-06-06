package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DepartmentEntity;
import com.kat.dmc.common.model.DepartmentEntity_;
import com.kat.dmc.repository.interfaces.DeptRepo;
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
public class DeptRepoImpl implements DeptRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DepartmentEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DepartmentEntity> criteriaQuery = builder.createQuery(DepartmentEntity.class);
        Root<DepartmentEntity> root = criteriaQuery.from(DepartmentEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DepartmentEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DepartmentEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DepartmentEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DepartmentEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DepartmentEntity findById(Integer userId) {
        return null;
    }
}
