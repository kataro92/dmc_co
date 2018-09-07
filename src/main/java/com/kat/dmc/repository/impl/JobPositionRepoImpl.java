package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.JobPositionEntity;
import com.kat.dmc.common.model.JobPositionEntity_;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class JobPositionRepoImpl implements JobPositionRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<JobPositionEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<JobPositionEntity> criteriaQuery = builder.createQuery(JobPositionEntity.class);
        Root<JobPositionEntity> root = criteriaQuery.from(JobPositionEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(JobPositionEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<JobPositionEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<JobPositionEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<JobPositionEntity> criteriaQuery = builder.createQuery(JobPositionEntity.class);
        Root<JobPositionEntity> root = criteriaQuery.from(JobPositionEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(JobPositionEntity_.status), 1));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<JobPositionEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(JobPositionEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(JobPositionEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public JobPositionEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<JobPositionEntity> criteriaQuery = builder.createQuery(JobPositionEntity.class);
        Root<JobPositionEntity> root = criteriaQuery.from(JobPositionEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(JobPositionEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<JobPositionEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
}
