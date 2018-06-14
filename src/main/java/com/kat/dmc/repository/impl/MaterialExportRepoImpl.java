package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcMaterialExportDetailEntity;
import com.kat.dmc.common.model.DmcMaterialExportDetailEntity_;
import com.kat.dmc.common.model.DmcMaterialExportEntity;
import com.kat.dmc.common.model.DmcMaterialExportEntity_;
import com.kat.dmc.repository.interfaces.MaterialExportRepo;
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
public class MaterialExportRepoImpl implements MaterialExportRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcMaterialExportEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialExportEntity> criteriaQuery = builder.createQuery(DmcMaterialExportEntity.class);
        Root<DmcMaterialExportEntity> root = criteriaQuery.from(DmcMaterialExportEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcMaterialExportEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialExportEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcMaterialExportEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialExportEntity> criteriaQuery = builder.createQuery(DmcMaterialExportEntity.class);
        Root<DmcMaterialExportEntity> root = criteriaQuery.from(DmcMaterialExportEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialExportEntity_.status), 0));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialExportEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcMaterialExportEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcMaterialExportEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcMaterialExportEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialExportEntity> criteriaQuery = builder.createQuery(DmcMaterialExportEntity.class);
        Root<DmcMaterialExportEntity> root = criteriaQuery.from(DmcMaterialExportEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialExportEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialExportEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<DmcMaterialExportDetailEntity> findAllActiveByMaterialImpId(int materialExportId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialExportDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialExportDetailEntity.class);
        Root<DmcMaterialExportDetailEntity> root = criteriaQuery.from(DmcMaterialExportDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialExportDetailEntity_.materialExportId), materialExportId));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialExportDetailEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcMaterialExportEntity> findAllActiveByWarehouseId(Integer warehouseId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialExportEntity> criteriaQuery = builder.createQuery(DmcMaterialExportEntity.class);
        Root<DmcMaterialExportEntity> root = criteriaQuery.from(DmcMaterialExportEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialExportEntity_.warehouseId), warehouseId));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialExportEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
