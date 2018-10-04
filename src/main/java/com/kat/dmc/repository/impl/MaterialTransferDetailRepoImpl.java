package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcMaterialTransferDetailEntity;
import com.kat.dmc.common.model.DmcMaterialTransferDetailEntity_;
import com.kat.dmc.repository.interfaces.MaterialTransferDetailRepo;
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
public class MaterialTransferDetailRepoImpl implements MaterialTransferDetailRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcMaterialTransferDetailEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialTransferDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialTransferDetailEntity.class);
        Root<DmcMaterialTransferDetailEntity> root = criteriaQuery.from(DmcMaterialTransferDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcMaterialTransferDetailEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialTransferDetailEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcMaterialTransferDetailEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialTransferDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialTransferDetailEntity.class);
        Root<DmcMaterialTransferDetailEntity> root = criteriaQuery.from(DmcMaterialTransferDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialTransferDetailEntity_.status), DEFAULT_ACTIVE.code()));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialTransferDetailEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcMaterialTransferDetailEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcMaterialTransferDetailEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcMaterialTransferDetailEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialTransferDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialTransferDetailEntity.class);
        Root<DmcMaterialTransferDetailEntity> root = criteriaQuery.from(DmcMaterialTransferDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialTransferDetailEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialTransferDetailEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            throw new RuntimeException("Single return empty result !");
        }
    }
}
