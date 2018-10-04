package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcMaterialGroupEntity;
import com.kat.dmc.common.model.DmcMaterialGroupEntity_;
import com.kat.dmc.repository.interfaces.MaterialGroupRepo;
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
public class MaterialGroupRepoImpl implements MaterialGroupRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcMaterialGroupEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialGroupEntity> criteriaQuery = builder.createQuery(DmcMaterialGroupEntity.class);
        Root<DmcMaterialGroupEntity> root = criteriaQuery.from(DmcMaterialGroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcMaterialGroupEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialGroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcMaterialGroupEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialGroupEntity> criteriaQuery = builder.createQuery(DmcMaterialGroupEntity.class);
        Root<DmcMaterialGroupEntity> root = criteriaQuery.from(DmcMaterialGroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialGroupEntity_.status), 1));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialGroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcMaterialGroupEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcMaterialGroupEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcMaterialGroupEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialGroupEntity> criteriaQuery = builder.createQuery(DmcMaterialGroupEntity.class);
        Root<DmcMaterialGroupEntity> root = criteriaQuery.from(DmcMaterialGroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialGroupEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialGroupEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            throw new RuntimeException("Single return empty result !");
        }
    }
}
