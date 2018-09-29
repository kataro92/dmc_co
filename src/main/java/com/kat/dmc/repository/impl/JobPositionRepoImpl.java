package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcJobPositionEntity;
import com.kat.dmc.common.model.DmcJobPositionEntity_;
import com.kat.dmc.repository.interfaces.JobPositionRepo;
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
import java.util.ArrayList;
import java.util.List;

@Repository
public class JobPositionRepoImpl implements JobPositionRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcJobPositionEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcJobPositionEntity> criteriaQuery = builder.createQuery(DmcJobPositionEntity.class);
        Root<DmcJobPositionEntity> root = criteriaQuery.from(DmcJobPositionEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcJobPositionEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcJobPositionEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcJobPositionEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcJobPositionEntity> criteriaQuery = builder.createQuery(DmcJobPositionEntity.class);
        Root<DmcJobPositionEntity> root = criteriaQuery.from(DmcJobPositionEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcJobPositionEntity_.status), 1));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcJobPositionEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcJobPositionEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcJobPositionEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcJobPositionEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcJobPositionEntity> criteriaQuery = builder.createQuery(DmcJobPositionEntity.class);
        Root<DmcJobPositionEntity> root = criteriaQuery.from(DmcJobPositionEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcJobPositionEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcJobPositionEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
}
