package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcWarehouseEntity;
import com.kat.dmc.common.model.DmcWarehouseEntity_;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.repository.interfaces.WarehouseRepo;
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
public class WarehouseRepoImpl implements WarehouseRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcWarehouseEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcWarehouseEntity> criteriaQuery = builder.createQuery(DmcWarehouseEntity.class);
        Root<DmcWarehouseEntity> root = criteriaQuery.from(DmcWarehouseEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcWarehouseEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcWarehouseEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcWarehouseEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcWarehouseEntity> criteriaQuery = builder.createQuery(DmcWarehouseEntity.class);
        Root<DmcWarehouseEntity> root = criteriaQuery.from(DmcWarehouseEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcWarehouseEntity_.status), 0));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcWarehouseEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcWarehouseEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcWarehouseEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcWarehouseEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcWarehouseEntity> criteriaQuery = builder.createQuery(DmcWarehouseEntity.class);
        Root<DmcWarehouseEntity> root = criteriaQuery.from(DmcWarehouseEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcWarehouseEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcWarehouseEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<DmcWarehouseEntity> findAllActiveByPermission(Boolean canImport, Boolean canExport, Boolean canTransfer, Boolean canDismiss) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcWarehouseEntity> criteriaQuery = builder.createQuery(DmcWarehouseEntity.class);
        Root<DmcWarehouseEntity> root = criteriaQuery.from(DmcWarehouseEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcWarehouseEntity_.status), 0));
        if(canImport != null) predicates.add(builder.equal(root.get(DmcWarehouseEntity_.canImport), canImport));
        if(canExport != null) predicates.add(builder.equal(root.get(DmcWarehouseEntity_.canExport), canExport));
        if(canTransfer != null) predicates.add(builder.equal(root.get(DmcWarehouseEntity_.canTransfer), canTransfer));
        if(canDismiss != null) predicates.add(builder.equal(root.get(DmcWarehouseEntity_.canDismiss), canDismiss));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcWarehouseEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
