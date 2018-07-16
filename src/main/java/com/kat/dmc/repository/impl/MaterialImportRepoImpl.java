package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.*;
import com.kat.dmc.repository.interfaces.MaterialImportRepo;
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
import java.sql.Time;
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

    @Override
    public List<MaterialImportDto> findAllActiveByMaterialId(Integer materialId) {
        String strSQL = "SELECT DM.id, DM.code, DM.category_id, DM.import_date, DM.supplier_id" +
                ", DM.status, DM.import_from_id" +
                ", DM.warehouse_id, DM.reson, DM.total, DM.import_from, sum(DTL.quantity) child_quantity" +
                " FROM dmc_material_import DM, dmc_material_import_detail DTL " +
                " WHERE DTL.material_id = ?1 AND DM.id = DTL.material_import_id" +
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
        importDto.setTotal((Long) objects[9]);
        importDto.setImportFrom((Integer) objects[10]);
        importDto.setChildQuantity((Integer) objects[11]);
        return importDto;
    }
}
