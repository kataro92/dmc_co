package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcMaterialExportDetailEntity;
import com.kat.dmc.common.model.DmcMaterialExportDetailEntity_;
import com.kat.dmc.common.model.DmcMaterialExportEntity;
import com.kat.dmc.common.model.DmcMaterialExportEntity_;
import com.kat.dmc.repository.interfaces.MaterialExportRepo;
import com.kat.dmc.repository.interfaces.UtilRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static com.kat.dmc.common.constant.CommonConst.Code.DEFAULT_ACTIVE;

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
        predicates.add(builder.equal(root.get(DmcMaterialExportEntity_.status), DEFAULT_ACTIVE.code()));
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
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            throw new RuntimeException("Single return empty result !");
        }
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


    @Override
    public Long countQuantityByWarehouseId(Integer warehouseId) {
        String strSQL = "select COUNT(COALESCE(1, 0)) SSA FROM dmc_material_export dmi "+
                " WHERE dmi.status=0 AND dmi.warehouse_id=?1";
        Query query = entityManager.createNativeQuery(strSQL);
        query.setParameter("1", warehouseId);
        try {
            BigInteger count = (BigInteger) query.getSingleResult();
            return count == null ? 0L : count.longValue();
        }catch (NoResultException ex){
            return 0L;
        }
    }
}
