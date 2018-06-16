package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.MaterialDto;
import com.kat.dmc.common.model.MaterialEntity;
import com.kat.dmc.common.model.MaterialEntity_;
import com.kat.dmc.repository.interfaces.MaterialRepo;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class MaterialRepoImpl implements MaterialRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<MaterialEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MaterialEntity> criteriaQuery = builder.createQuery(MaterialEntity.class);
        Root<MaterialEntity> root = criteriaQuery.from(MaterialEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(MaterialEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<MaterialEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<MaterialEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MaterialEntity> criteriaQuery = builder.createQuery(MaterialEntity.class);
        Root<MaterialEntity> root = criteriaQuery.from(MaterialEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(MaterialEntity_.status), 0));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<MaterialEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(MaterialEntity userEntity) {
        userEntity.setLastModified(new Timestamp(new Date().getTime()));
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(MaterialEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public MaterialEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MaterialEntity> criteriaQuery = builder.createQuery(MaterialEntity.class);
        Root<MaterialEntity> root = criteriaQuery.from(MaterialEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(MaterialEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<MaterialEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<MaterialEntity> findBySubgroupId(int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MaterialEntity> criteriaQuery = builder.createQuery(MaterialEntity.class);
        Root<MaterialEntity> root = criteriaQuery.from(MaterialEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(MaterialEntity_.materialSubgroupCode), String.valueOf(id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<MaterialEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public MaterialEntity findByCode(String code) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MaterialEntity> criteriaQuery = builder.createQuery(MaterialEntity.class);
        Root<MaterialEntity> root = criteriaQuery.from(MaterialEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(MaterialEntity_.code), code));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<MaterialEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<MaterialDto> findAllByImport() {
        String strSQL = "SELECT MS._id, " +
                "MS.code, " +
                "MS.material_group_code, " +
                "MS.material_subgroup_code, " +
                "MS.name, " +
                "MS.status " +
                "FROM material MS " +
                "WHERE MS.status=0";
        Query query = entityManager.createNativeQuery(strSQL);
        List<Object[]> lstResult = query.getResultList();
        List<MaterialDto> lstReturn = new ArrayList<>();
        for(Object[] objects : lstResult){
            lstReturn.add(object2Dto(objects));
        }
        return lstReturn;
    }

    private MaterialDto object2Dto(Object[] objects) {
        MaterialDto materialDto = new MaterialDto();
        materialDto.setId((Integer) objects[0]);
        materialDto.setCode((String) objects[1]);
        materialDto.setMaterialGroupCode((String) objects[2]);
        materialDto.setMaterialSubgroupCode((String) objects[3]);
        materialDto.setName((String) objects[4]);
        materialDto.setStatus((Integer) objects[5]);
//        materialDto.setCurrentImportId((Integer) objects[6]);
//        materialDto.setCurrentImportCode((String) objects[7]);
//        materialDto.setCurrentPrice((Integer) objects[8]);
        materialDto.setCurrentPrice((Integer) 100);
        return materialDto;
    }
}
