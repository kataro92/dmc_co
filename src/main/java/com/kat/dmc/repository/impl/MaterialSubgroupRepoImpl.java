package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.MaterialSubgroupEntity;
import com.kat.dmc.common.model.MaterialSubgroupEntity_;
import com.kat.dmc.repository.interfaces.MaterialSubgroupRepo;
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
public class MaterialSubgroupRepoImpl implements MaterialSubgroupRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<MaterialSubgroupEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MaterialSubgroupEntity> criteriaQuery = builder.createQuery(MaterialSubgroupEntity.class);
        Root<MaterialSubgroupEntity> root = criteriaQuery.from(MaterialSubgroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(MaterialSubgroupEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<MaterialSubgroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<MaterialSubgroupEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MaterialSubgroupEntity> criteriaQuery = builder.createQuery(MaterialSubgroupEntity.class);
        Root<MaterialSubgroupEntity> root = criteriaQuery.from(MaterialSubgroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(MaterialSubgroupEntity_.status), 0));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<MaterialSubgroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(MaterialSubgroupEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(MaterialSubgroupEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public MaterialSubgroupEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MaterialSubgroupEntity> criteriaQuery = builder.createQuery(MaterialSubgroupEntity.class);
        Root<MaterialSubgroupEntity> root = criteriaQuery.from(MaterialSubgroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(MaterialSubgroupEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<MaterialSubgroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<MaterialSubgroupEntity> findAllByGroupId(int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MaterialSubgroupEntity> criteriaQuery = builder.createQuery(MaterialSubgroupEntity.class);
        Root<MaterialSubgroupEntity> root = criteriaQuery.from(MaterialSubgroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(MaterialSubgroupEntity_.materialGroupCode), String.valueOf(id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<MaterialSubgroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
