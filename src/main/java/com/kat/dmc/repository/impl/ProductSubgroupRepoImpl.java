package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.ProductSubgroupEntity;
import com.kat.dmc.common.model.ProductSubgroupEntity_;
import com.kat.dmc.repository.interfaces.ProductSubgroupRepo;
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
public class ProductSubgroupRepoImpl implements ProductSubgroupRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<ProductSubgroupEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductSubgroupEntity> criteriaQuery = builder.createQuery(ProductSubgroupEntity.class);
        Root<ProductSubgroupEntity> root = criteriaQuery.from(ProductSubgroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(ProductSubgroupEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<ProductSubgroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<ProductSubgroupEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductSubgroupEntity> criteriaQuery = builder.createQuery(ProductSubgroupEntity.class);
        Root<ProductSubgroupEntity> root = criteriaQuery.from(ProductSubgroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(ProductSubgroupEntity_.status), 0));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<ProductSubgroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(ProductSubgroupEntity userEntity) {
        userEntity.setLastModified(new Timestamp(new Date().getTime()));
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(ProductSubgroupEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public ProductSubgroupEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductSubgroupEntity> criteriaQuery = builder.createQuery(ProductSubgroupEntity.class);
        Root<ProductSubgroupEntity> root = criteriaQuery.from(ProductSubgroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(ProductSubgroupEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<ProductSubgroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<ProductSubgroupEntity> findAllByGroupId(int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductSubgroupEntity> criteriaQuery = builder.createQuery(ProductSubgroupEntity.class);
        Root<ProductSubgroupEntity> root = criteriaQuery.from(ProductSubgroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(ProductSubgroupEntity_.productGroupCode), String.valueOf(id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<ProductSubgroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
