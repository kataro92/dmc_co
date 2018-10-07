package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcMaterialExportDetailEntity;
import com.kat.dmc.common.model.DmcMaterialExportDetailEntity_;
import com.kat.dmc.repository.interfaces.MaterialExportDetailRepo;
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
import java.util.logging.Logger;

import static com.kat.dmc.common.constant.CommonConst.Code.DEFAULT_ACTIVE;

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
        predicates.add(builder.equal(root.get(DmcMaterialExportDetailEntity_.status), DEFAULT_ACTIVE.code()));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialExportDetailEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcMaterialExportDetailEntity exportDetailEntity) {
        entityManager.merge(exportDetailEntity);
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
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            Logger.getLogger(this.getClass().getName()).warning(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<DmcMaterialExportDetailEntity> findByIdMaterialId(int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialExportDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialExportDetailEntity.class);
        Root<DmcMaterialExportDetailEntity> root = criteriaQuery.from(DmcMaterialExportDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialExportDetailEntity_.status), DEFAULT_ACTIVE.code()));
        predicates.add(builder.equal(root.get(DmcMaterialExportDetailEntity_.materialId), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialExportDetailEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public Long countQuantityByWarehouseId(Integer warehouseId) {
        String strSQL = "select SUM(COALESCE(dmid.quantity, 0)) SSA FROM dmc_material_export_detail dmid " +
                "WHERE dmid.status = "+DEFAULT_ACTIVE.code()+" AND dmid.material_export_id " +
                "IN (select dmi.id FROM dmc_material_export dmi " +
                "WHERE dmi.status="+DEFAULT_ACTIVE.code()+" AND dmi.warehouse_id=?1)";
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
