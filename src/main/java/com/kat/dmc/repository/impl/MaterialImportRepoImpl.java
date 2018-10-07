package com.kat.dmc.repository.impl;

import com.kat.dmc.common.dto.MaterialImportDto;
import com.kat.dmc.common.model.DmcMaterialImportDetailEntity;
import com.kat.dmc.common.model.DmcMaterialImportDetailEntity_;
import com.kat.dmc.common.model.DmcMaterialImportEntity;
import com.kat.dmc.common.model.DmcMaterialImportEntity_;
import com.kat.dmc.repository.interfaces.MaterialImportRepo;
import com.kat.dmc.repository.interfaces.UtilRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.kat.dmc.common.constant.CommonConst.Code.DEFAULT_ACTIVE;

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
        predicates.add(builder.equal(root.get(DmcMaterialImportEntity_.status), DEFAULT_ACTIVE.code()));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialImportEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<MaterialImportDto> findAllActiveQuantity() {
        String strSQL = "SELECT DM.id, DM.code, DM.category_id, DM.import_date, DM.supplier_id" +
                ", DM.status, DM.import_from_id" +
                ", DM.warehouse_id, DM.reson, DM.total, DM.import_from, DTL.quantity child_quantity" +
                " FROM dmc_material_import DM, dmc_material_import_detail DTL " +
                " WHERE DM.id = DTL.material_import_id " +
                " UNION ALL" +
                " SELECT DM.id, DM.code, DM.category_id, DM.import_date, DM.supplier_id" +
                ", DM.status, DM.import_from_id" +
                ", DM.warehouse_id, DM.reson, DM.total, DM.import_from, DTL.quantity child_quantity" +
                " FROM dmc_material_export DM, dmc_material_import_detail DTL " +
                " WHERE DM.id = DTL.material_import_id " +
                " ORDER BY DM.import_date";
        Query query = entityManager.createNativeQuery(strSQL);
        List<Object[]> lstResult = query.getResultList();
        List<MaterialImportDto> lstReturn = new ArrayList<>();
        for(Object[] objects : lstResult){
            lstReturn.add(object2Dto(objects));
        }
        return lstReturn;
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
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            Logger.getLogger(this.getClass().getName()).warning(ex.getMessage());
            return null;
        }
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
    public List<DmcMaterialImportDetailEntity> findAllActiveByMaterialIdImpId(int materialImportId, int materialId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialImportDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialImportDetailEntity.class);
        Root<DmcMaterialImportDetailEntity> root = criteriaQuery.from(DmcMaterialImportDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialImportDetailEntity_.materialImportId), materialImportId));
        predicates.add(builder.equal(root.get(DmcMaterialImportDetailEntity_.materialId), materialId));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialImportDetailEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
    @Override
    public List<DmcMaterialImportDetailEntity> findAllActiveByMaterialIdImpIds(List<Integer> materialImportIds, int materialId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialImportDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialImportDetailEntity.class);
        Root<DmcMaterialImportDetailEntity> root = criteriaQuery.from(DmcMaterialImportDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(root.get(DmcMaterialImportDetailEntity_.materialImportId).in(materialImportIds));
        predicates.add(builder.equal(root.get(DmcMaterialImportDetailEntity_.materialId), materialId));
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

    @Override
    public List<MaterialImportDto> findAllActiveByMaterialId(Integer materialId) {
        String strSQL = "SELECT DM.id, DM.code, DM.category_id, DM.import_date, DM.supplier_id" +
                ", DM.status, DM.import_from_id" +
                ", DM.warehouse_id, DM.reson, DM.total, DM.import_from, sum(DTL.quantity) child_quantity" +
                " FROM dmc_material_import DM, dmc_material_import_detail DTL " +
                " WHERE DTL.material_id = ?1 AND DM.id = DTL.material_import_id" +
                " GROUP BY DM.id, DM.code, DM.category_id, DM.import_date, DM.supplier_id" +
                ", DM.status, DM.import_from_id" +
                ", DM.warehouse_id, DM.reson, DM.total, DM.import_from " +
                " ORDER BY DM.import_date";
        Query query = entityManager.createNativeQuery(strSQL);
        query.setParameter("1", materialId);
        List<Object[]> lstResult = query.getResultList();
        List<MaterialImportDto> lstReturn = new ArrayList<>();
        for(Object[] objects : lstResult){
            lstReturn.add(object2Dto(objects));
        }
        return lstReturn;
    }

    private MaterialImportDto object2Dto(Object[] objects) {
        MaterialImportDto importDto = new MaterialImportDto();
        importDto.setId((Integer) objects[0]);
        importDto.setCode((String) objects[1]);
        importDto.setCategoryId((Integer) objects[2]);
        importDto.setImportDate((Timestamp) objects[3]);
        importDto.setSupplierId((Integer) objects[4]);
        importDto.setStatus((Integer) objects[5]);
        importDto.setImportFromId((Integer) objects[6]);
        importDto.setWarehouseId((Integer) objects[7]);
        importDto.setReson((String) objects[8]);
        importDto.setTotal(((BigInteger) objects[9]).longValue());
        importDto.setImportFrom((Integer) objects[10]);
        importDto.setChildQuantity(((BigInteger) objects[11]).intValue());
        return importDto;
    }

    @Override
    public Long countQuantityByWarehouseId(Integer warehouseId) {
        String strSQL = "select COUNT(COALESCE(1, 0)) SSA FROM dmc_material_import dmi "+
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
