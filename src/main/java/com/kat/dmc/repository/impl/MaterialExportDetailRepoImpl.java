package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcMaterialExportDetailEntity;
import com.kat.dmc.common.model.DmcMaterialExportDetailEntity_;
import com.kat.dmc.common.model.DmcMaterialImportDetailEntity;
import com.kat.dmc.repository.interfaces.MaterialExportDetailRepo;
import com.kat.dmc.repository.interfaces.UtilRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MaterialExportDetailRepoImpl implements MaterialExportDetailRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcMaterialExportDetailEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialExportDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialExportDetailEntity.class);
        Root<DmcMaterialExportDetailEntity> root = criteriaQuery.from(DmcMaterialExportDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcMaterialExportDetailEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialExportDetailEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcMaterialExportDetailEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialExportDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialExportDetailEntity.class);
        Root<DmcMaterialExportDetailEntity> root = criteriaQuery.from(DmcMaterialExportDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialExportDetailEntity_.status), 0));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialExportDetailEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcMaterialExportDetailEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcMaterialExportDetailEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcMaterialExportDetailEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialExportDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialExportDetailEntity.class);
        Root<DmcMaterialExportDetailEntity> root = criteriaQuery.from(DmcMaterialExportDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialExportDetailEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialExportDetailEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<DmcMaterialExportDetailEntity> findByIdMaterialId(int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialExportDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialExportDetailEntity.class);
        Root<DmcMaterialExportDetailEntity> root = criteriaQuery.from(DmcMaterialExportDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialExportDetailEntity_.status), 0));
        predicates.add(builder.equal(root.get(DmcMaterialExportDetailEntity_.materialId), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialExportDetailEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public Long countQuantityByWarehouseId(Integer warehouseId) {
        String strSQL = "select SUM(COALESCE(dmid.quantity, 0)) SSA FROM dmc_material_export_detail dmid " +
                "WHERE dmid.status = 0 AND dmid.material_export_id " +
                "IN (select dmi.id FROM dmc_material_export dmi " +
                "WHERE dmi.status=0 AND dmi.warehouse_id=?1)";
        Query query = entityManager.createNativeQuery(strSQL);
        query.setParameter("1", warehouseId);
        BigInteger totalQuantity = (BigInteger) query.getSingleResult();
        if(totalQuantity == null){
            return 0l;
        }
        return totalQuantity.longValue();
    }
}
