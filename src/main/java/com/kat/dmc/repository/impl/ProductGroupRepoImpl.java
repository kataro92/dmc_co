package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.ProductGroupEntity;
import com.kat.dmc.common.model.ProductGroupEntity_;
import com.kat.dmc.repository.interfaces.ProductGroupRepo;
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
public class ProductGroupRepoImpl implements ProductGroupRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<ProductGroupEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductGroupEntity> criteriaQuery = builder.createQuery(ProductGroupEntity.class);
        Root<ProductGroupEntity> root = criteriaQuery.from(ProductGroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(ProductGroupEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<ProductGroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<ProductGroupEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductGroupEntity> criteriaQuery = builder.createQuery(ProductGroupEntity.class);
        Root<ProductGroupEntity> root = criteriaQuery.from(ProductGroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(ProductGroupEntity_.status), 1));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<ProductGroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(ProductGroupEntity userEntity) {
        userEntity.setLastModified(new Timestamp(new Date().getTime()));
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(ProductGroupEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public ProductGroupEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductGroupEntity> criteriaQuery = builder.createQuery(ProductGroupEntity.class);
        Root<ProductGroupEntity> root = criteriaQuery.from(ProductGroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(ProductGroupEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<ProductGroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
}
