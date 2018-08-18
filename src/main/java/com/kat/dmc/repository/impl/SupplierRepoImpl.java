package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.SupplierEntity;
import com.kat.dmc.common.model.SupplierEntity_;
import com.kat.dmc.repository.interfaces.SupplierRepo;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class SupplierRepoImpl implements SupplierRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<SupplierEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SupplierEntity> criteriaQuery = builder.createQuery(SupplierEntity.class);
        Root<SupplierEntity> root = criteriaQuery.from(SupplierEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(SupplierEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<SupplierEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<SupplierEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SupplierEntity> criteriaQuery = builder.createQuery(SupplierEntity.class);
        Root<SupplierEntity> root = criteriaQuery.from(SupplierEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(SupplierEntity_.status), 1));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<SupplierEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(SupplierEntity userEntity) {
        userEntity.setLastModified(new Timestamp(new Date().getTime()));
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(SupplierEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public SupplierEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SupplierEntity> criteriaQuery = builder.createQuery(SupplierEntity.class);
        Root<SupplierEntity> root = criteriaQuery.from(SupplierEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(SupplierEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<SupplierEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
}
