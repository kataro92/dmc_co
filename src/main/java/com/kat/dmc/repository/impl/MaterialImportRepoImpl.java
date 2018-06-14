package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcMaterialImportDetailEntity;
import com.kat.dmc.common.model.DmcMaterialImportDetailEntity_;
import com.kat.dmc.common.model.DmcMaterialImportEntity;
import com.kat.dmc.common.model.DmcMaterialImportEntity_;
import com.kat.dmc.repository.interfaces.MaterialImportRepo;
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
public class MaterialImportRepoImpl implements MaterialImportRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcMaterialImportEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialImportEntity> criteriaQuery = builder.createQuery(DmcMaterialImportEntity.class);
        Root<DmcMaterialImportEntity> root = criteriaQuery.from(DmcMaterialImportEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcMaterialImportEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialImportEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcMaterialImportEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialImportEntity> criteriaQuery = builder.createQuery(DmcMaterialImportEntity.class);
        Root<DmcMaterialImportEntity> root = criteriaQuery.from(DmcMaterialImportEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialImportEntity_.status), 0));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialImportEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcMaterialImportEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcMaterialImportEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcMaterialImportEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialImportEntity> criteriaQuery = builder.createQuery(DmcMaterialImportEntity.class);
        Root<DmcMaterialImportEntity> root = criteriaQuery.from(DmcMaterialImportEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialImportEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialImportEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<DmcMaterialImportDetailEntity> findAllActiveByMaterialImpId(int materialImportId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialImportDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialImportDetailEntity.class);
        Root<DmcMaterialImportDetailEntity> root = criteriaQuery.from(DmcMaterialImportDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialImportDetailEntity_.materialImportId), materialImportId));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialImportDetailEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcMaterialImportEntity> findAllActiveByWarehouseId(Integer warehouseId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialImportEntity> criteriaQuery = builder.createQuery(DmcMaterialImportEntity.class);
        Root<DmcMaterialImportEntity> root = criteriaQuery.from(DmcMaterialImportEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialImportEntity_.warehouseId), warehouseId));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialImportEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
