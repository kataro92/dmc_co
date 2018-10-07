package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcMaterialDismissDetailEntity;
import com.kat.dmc.common.model.DmcMaterialDismissDetailEntity_;
import com.kat.dmc.repository.interfaces.MaterialDismissDetailRepo;
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
public class MaterialDismissDetailRepoImpl implements MaterialDismissDetailRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcMaterialDismissDetailEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialDismissDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialDismissDetailEntity.class);
        Root<DmcMaterialDismissDetailEntity> root = criteriaQuery.from(DmcMaterialDismissDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcMaterialDismissDetailEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialDismissDetailEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcMaterialDismissDetailEntity> findAllActive() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialDismissDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialDismissDetailEntity.class);
        Root<DmcMaterialDismissDetailEntity> root = criteriaQuery.from(DmcMaterialDismissDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialDismissDetailEntity_.status), DEFAULT_ACTIVE.code()));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialDismissDetailEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcMaterialDismissDetailEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcMaterialDismissDetailEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcMaterialDismissDetailEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcMaterialDismissDetailEntity> criteriaQuery = builder.createQuery(DmcMaterialDismissDetailEntity.class);
        Root<DmcMaterialDismissDetailEntity> root = criteriaQuery.from(DmcMaterialDismissDetailEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcMaterialDismissDetailEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcMaterialDismissDetailEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            Logger.getLogger(this.getClass().getName()).warning(ex.getMessage());
            return null;
        }
    }
}
