package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcProductEntity;
import com.kat.dmc.common.model.DmcProductEntity_;
import com.kat.dmc.repository.interfaces.ProductRepo;
import com.kat.dmc.repository.interfaces.UtilRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ProductRepoImpl implements ProductRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcProductEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcProductEntity> criteriaQuery = builder.createQuery(DmcProductEntity.class);
        Root<DmcProductEntity> root = criteriaQuery.from(DmcProductEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcProductEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcProductEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcProductEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcProductEntity> criteriaQuery = builder.createQuery(DmcProductEntity.class);
        Root<DmcProductEntity> root = criteriaQuery.from(DmcProductEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcProductEntity_.status), 1));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcProductEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcProductEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcProductEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcProductEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcProductEntity> criteriaQuery = builder.createQuery(DmcProductEntity.class);
        Root<DmcProductEntity> root = criteriaQuery.from(DmcProductEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcProductEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcProductEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            Logger.getLogger(this.getClass().getName()).warning(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<DmcProductEntity> findBySubgroupId(int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcProductEntity> criteriaQuery = builder.createQuery(DmcProductEntity.class);
        Root<DmcProductEntity> root = criteriaQuery.from(DmcProductEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcProductEntity_.productSubgroupCode), String.valueOf(id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcProductEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
