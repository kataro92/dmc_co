package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcSupplierEntity;
import com.kat.dmc.common.model.DmcSupplierEntity_;
import com.kat.dmc.repository.interfaces.SupplierRepo;
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
public class SupplierRepoImpl implements SupplierRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcSupplierEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcSupplierEntity> criteriaQuery = builder.createQuery(DmcSupplierEntity.class);
        Root<DmcSupplierEntity> root = criteriaQuery.from(DmcSupplierEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcSupplierEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcSupplierEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcSupplierEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcSupplierEntity> criteriaQuery = builder.createQuery(DmcSupplierEntity.class);
        Root<DmcSupplierEntity> root = criteriaQuery.from(DmcSupplierEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcSupplierEntity_.status), 1));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcSupplierEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcSupplierEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcSupplierEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcSupplierEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcSupplierEntity> criteriaQuery = builder.createQuery(DmcSupplierEntity.class);
        Root<DmcSupplierEntity> root = criteriaQuery.from(DmcSupplierEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcSupplierEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcSupplierEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            throw new RuntimeException("Single return empty result !");
        }
    }
}
