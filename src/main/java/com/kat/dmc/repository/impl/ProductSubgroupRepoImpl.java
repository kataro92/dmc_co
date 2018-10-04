package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcProductSubgroupEntity;
import com.kat.dmc.common.model.DmcProductSubgroupEntity_;
import com.kat.dmc.repository.interfaces.ProductSubgroupRepo;
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

@Repository
public class ProductSubgroupRepoImpl implements ProductSubgroupRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcProductSubgroupEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcProductSubgroupEntity> criteriaQuery = builder.createQuery(DmcProductSubgroupEntity.class);
        Root<DmcProductSubgroupEntity> root = criteriaQuery.from(DmcProductSubgroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcProductSubgroupEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcProductSubgroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcProductSubgroupEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcProductSubgroupEntity> criteriaQuery = builder.createQuery(DmcProductSubgroupEntity.class);
        Root<DmcProductSubgroupEntity> root = criteriaQuery.from(DmcProductSubgroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcProductSubgroupEntity_.status), 1));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcProductSubgroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcProductSubgroupEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcProductSubgroupEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcProductSubgroupEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcProductSubgroupEntity> criteriaQuery = builder.createQuery(DmcProductSubgroupEntity.class);
        Root<DmcProductSubgroupEntity> root = criteriaQuery.from(DmcProductSubgroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcProductSubgroupEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcProductSubgroupEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            throw new RuntimeException("Single return empty result !");
        }
    }

    @Override
    public List<DmcProductSubgroupEntity> findAllByGroupId(int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcProductSubgroupEntity> criteriaQuery = builder.createQuery(DmcProductSubgroupEntity.class);
        Root<DmcProductSubgroupEntity> root = criteriaQuery.from(DmcProductSubgroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcProductSubgroupEntity_.productGroupCode), String.valueOf(id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcProductSubgroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
