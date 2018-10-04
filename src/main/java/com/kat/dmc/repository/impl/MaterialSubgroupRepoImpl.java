package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcMaterialSubgroupEntity;
import com.kat.dmc.common.model.DmcMaterialSubgroupEntity_;
import com.kat.dmc.repository.interfaces.MaterialSubgroupRepo;
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

import static com.kat.dmc.common.constant.CommonConst.Code.DEFAULT_ACTIVE;

@Repository
public class MaterialSubgroupRepoImpl implements MaterialSubgroupRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcMaterialSubgroupEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialSubgroupEntity> criteriaQuery = builder.createQuery(DmcMaterialSubgroupEntity.class);
        Root<DmcMaterialSubgroupEntity> root = criteriaQuery.from(DmcMaterialSubgroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcMaterialSubgroupEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialSubgroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcMaterialSubgroupEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialSubgroupEntity> criteriaQuery = builder.createQuery(DmcMaterialSubgroupEntity.class);
        Root<DmcMaterialSubgroupEntity> root = criteriaQuery.from(DmcMaterialSubgroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialSubgroupEntity_.status), DEFAULT_ACTIVE.code()));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialSubgroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcMaterialSubgroupEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcMaterialSubgroupEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcMaterialSubgroupEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialSubgroupEntity> criteriaQuery = builder.createQuery(DmcMaterialSubgroupEntity.class);
        Root<DmcMaterialSubgroupEntity> root = criteriaQuery.from(DmcMaterialSubgroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialSubgroupEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialSubgroupEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            throw new RuntimeException("Single return empty result !");
        }
    }

    @Override
    public List<DmcMaterialSubgroupEntity> findAllByGroupId(int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialSubgroupEntity> criteriaQuery = builder.createQuery(DmcMaterialSubgroupEntity.class);
        Root<DmcMaterialSubgroupEntity> root = criteriaQuery.from(DmcMaterialSubgroupEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialSubgroupEntity_.materialGroupCode), String.valueOf(id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialSubgroupEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
