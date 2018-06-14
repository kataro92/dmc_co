package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.MaterialEntity;
import com.kat.dmc.common.model.MaterialEntity_;
import com.kat.dmc.repository.interfaces.MaterialRepo;
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
}
