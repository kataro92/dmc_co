package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcMaterialTransferDetailEntity;
import com.kat.dmc.common.model.DmcMaterialTransferDetailEntity_;
import com.kat.dmc.common.model.DmcMaterialTransferEntity;
import com.kat.dmc.common.model.DmcMaterialTransferEntity_;
import com.kat.dmc.repository.interfaces.MaterialTransferRepo;
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
import java.util.logging.Logger;

import static com.kat.dmc.common.constant.CommonConst.Code.DEFAULT_ACTIVE;

@Repository
public class MaterialTransferRepoImpl implements MaterialTransferRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcMaterialTransferEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialTransferEntity> criteriaQuery = builder.createQuery(DmcMaterialTransferEntity.class);
        Root<DmcMaterialTransferEntity> root = criteriaQuery.from(DmcMaterialTransferEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcMaterialTransferEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialTransferEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcMaterialTransferEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialTransferEntity> criteriaQuery = builder.createQuery(DmcMaterialTransferEntity.class);
        Root<DmcMaterialTransferEntity> root = criteriaQuery.from(DmcMaterialTransferEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialTransferEntity_.status), DEFAULT_ACTIVE.code()));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialTransferEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcMaterialTransferEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcMaterialTransferEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcMaterialTransferEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialTransferEntity> criteriaQuery = builder.createQuery(DmcMaterialTransferEntity.class);
        Root<DmcMaterialTransferEntity> root = criteriaQuery.from(DmcMaterialTransferEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialTransferEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialTransferEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            Logger.getLogger(this.getClass().getName()).warning(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<DmcMaterialTransferDetailEntity> findAllActiveByMaterialImpId(int materialTransferId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialTransferDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialTransferDetailEntity.class);
        Root<DmcMaterialTransferDetailEntity> root = criteriaQuery.from(DmcMaterialTransferDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialTransferDetailEntity_.materialTransferId), materialTransferId));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialTransferDetailEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcMaterialTransferEntity> findAllActiveByWarehouseId(Integer warehouseId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialTransferEntity> criteriaQuery = builder.createQuery(DmcMaterialTransferEntity.class);
        Root<DmcMaterialTransferEntity> root = criteriaQuery.from(DmcMaterialTransferEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialTransferEntity_.warehouseId), warehouseId));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialTransferEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
