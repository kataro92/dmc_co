package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcMaterialImportDetailEntity;
import com.kat.dmc.common.model.DmcMaterialImportDetailEntity_;
import com.kat.dmc.repository.interfaces.MaterialImportDetailRepo;
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
public class MaterialImportDetailRepoImpl implements MaterialImportDetailRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcMaterialImportDetailEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialImportDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialImportDetailEntity.class);
        Root<DmcMaterialImportDetailEntity> root = criteriaQuery.from(DmcMaterialImportDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcMaterialImportDetailEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialImportDetailEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcMaterialImportDetailEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialImportDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialImportDetailEntity.class);
        Root<DmcMaterialImportDetailEntity> root = criteriaQuery.from(DmcMaterialImportDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialImportDetailEntity_.status), 0));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialImportDetailEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcMaterialImportDetailEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcMaterialImportDetailEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcMaterialImportDetailEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialImportDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialImportDetailEntity.class);
        Root<DmcMaterialImportDetailEntity> root = criteriaQuery.from(DmcMaterialImportDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialImportDetailEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialImportDetailEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<DmcMaterialImportDetailEntity> findByIdMaterialId(int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialImportDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialImportDetailEntity.class);
        Root<DmcMaterialImportDetailEntity> root = criteriaQuery.from(DmcMaterialImportDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialImportDetailEntity_.status), 0));
        predicates.add(builder.equal(root.get(DmcMaterialImportDetailEntity_.materialId), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new))
                .orderBy(builder.asc(root.get(DmcMaterialImportDetailEntity_.id)));
        final TypedQuery<DmcMaterialImportDetailEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public Long countQuantityByWarehouseId(Integer warehouseId) {
        String strSQL = "select SUM(COALESCE(dmid.quantity, 0)) SSA FROM dmc_material_import_detail dmid " +
                "WHERE dmid.status = 0 AND dmid.material_import_id " +
                "IN (select dmi.id FROM dmc_material_import dmi " +
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
