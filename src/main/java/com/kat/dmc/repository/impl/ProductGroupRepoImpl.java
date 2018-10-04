package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcProductGroupEntity;
import com.kat.dmc.common.model.DmcProductGroupEntity_;
import com.kat.dmc.repository.interfaces.ProductGroupRepo;
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
public class ProductGroupRepoImpl implements ProductGroupRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcProductGroupEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcProductGroupEntity> criteriaQuery = builder.createQuery(DmcProductGroupEntity.class);
        Root<DmcProductGroupEntity> root = criteriaQuery.from(DmcProductGroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcProductGroupEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcProductGroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcProductGroupEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcProductGroupEntity> criteriaQuery = builder.createQuery(DmcProductGroupEntity.class);
        Root<DmcProductGroupEntity> root = criteriaQuery.from(DmcProductGroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcProductGroupEntity_.status), 1));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcProductGroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcProductGroupEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcProductGroupEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcProductGroupEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcProductGroupEntity> criteriaQuery = builder.createQuery(DmcProductGroupEntity.class);
        Root<DmcProductGroupEntity> root = criteriaQuery.from(DmcProductGroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcProductGroupEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcProductGroupEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            throw new RuntimeException("Single return empty result !");
        }
    }
}
