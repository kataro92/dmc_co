package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.MaterialGroupEntity;
import com.kat.dmc.common.model.MaterialGroupEntity_;
import com.kat.dmc.repository.interfaces.MaterialGroupRepo;
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
public class MaterialGroupRepoImpl implements MaterialGroupRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<MaterialGroupEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MaterialGroupEntity> criteriaQuery = builder.createQuery(MaterialGroupEntity.class);
        Root<MaterialGroupEntity> root = criteriaQuery.from(MaterialGroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(MaterialGroupEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<MaterialGroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<MaterialGroupEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MaterialGroupEntity> criteriaQuery = builder.createQuery(MaterialGroupEntity.class);
        Root<MaterialGroupEntity> root = criteriaQuery.from(MaterialGroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(MaterialGroupEntity_.status), 1));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<MaterialGroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(MaterialGroupEntity userEntity) {
        userEntity.setLastModified(new Timestamp(new Date().getTime()));
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(MaterialGroupEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public MaterialGroupEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MaterialGroupEntity> criteriaQuery = builder.createQuery(MaterialGroupEntity.class);
        Root<MaterialGroupEntity> root = criteriaQuery.from(MaterialGroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(MaterialGroupEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<MaterialGroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
}
