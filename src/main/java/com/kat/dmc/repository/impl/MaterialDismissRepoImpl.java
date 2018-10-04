package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcMaterialDismissDetailEntity;
import com.kat.dmc.common.model.DmcMaterialDismissDetailEntity_;
import com.kat.dmc.common.model.DmcMaterialDismissEntity;
import com.kat.dmc.common.model.DmcMaterialDismissEntity_;
import com.kat.dmc.repository.interfaces.MaterialDismissRepo;
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
public class MaterialDismissRepoImpl implements MaterialDismissRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcMaterialDismissEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialDismissEntity> criteriaQuery = builder.createQuery(DmcMaterialDismissEntity.class);
        Root<DmcMaterialDismissEntity> root = criteriaQuery.from(DmcMaterialDismissEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcMaterialDismissEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialDismissEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcMaterialDismissEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialDismissEntity> criteriaQuery = builder.createQuery(DmcMaterialDismissEntity.class);
        Root<DmcMaterialDismissEntity> root = criteriaQuery.from(DmcMaterialDismissEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialDismissEntity_.status), DEFAULT_ACTIVE.code()));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialDismissEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcMaterialDismissEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcMaterialDismissEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcMaterialDismissEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialDismissEntity> criteriaQuery = builder.createQuery(DmcMaterialDismissEntity.class);
        Root<DmcMaterialDismissEntity> root = criteriaQuery.from(DmcMaterialDismissEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialDismissEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialDismissEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            throw new RuntimeException("Single return empty result !");
        }
    }

    @Override
    public List<DmcMaterialDismissDetailEntity> findAllActiveByMaterialImpId(int materialDismissId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialDismissDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialDismissDetailEntity.class);
        Root<DmcMaterialDismissDetailEntity> root = criteriaQuery.from(DmcMaterialDismissDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialDismissDetailEntity_.materialDismissId), materialDismissId));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialDismissDetailEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcMaterialDismissEntity> findAllActiveByWarehouseId(Integer warehouseId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialDismissEntity> criteriaQuery = builder.createQuery(DmcMaterialDismissEntity.class);
        Root<DmcMaterialDismissEntity> root = criteriaQuery.from(DmcMaterialDismissEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialDismissEntity_.warehouseId), warehouseId));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialDismissEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
