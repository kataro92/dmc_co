package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcBuildingMaterialEntity;
import com.kat.dmc.common.model.DmcBuildingMaterialEntity_;
import com.kat.dmc.repository.interfaces.BuildingMaterialRepo;
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

@Repository
public class BuildingMaterialImpl implements BuildingMaterialRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcBuildingMaterialEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcBuildingMaterialEntity> criteriaQuery = builder.createQuery(DmcBuildingMaterialEntity.class);
        Root<DmcBuildingMaterialEntity> root = criteriaQuery.from(DmcBuildingMaterialEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcBuildingMaterialEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcBuildingMaterialEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcBuildingMaterialEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcBuildingMaterialEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcBuildingMaterialEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcBuildingMaterialEntity> criteriaQuery = builder.createQuery(DmcBuildingMaterialEntity.class);
        Root<DmcBuildingMaterialEntity> root = criteriaQuery.from(DmcBuildingMaterialEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcBuildingMaterialEntity_.id)));
        predicates.add(builder.equal(root.get(DmcBuildingMaterialEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcBuildingMaterialEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            Logger.getLogger(this.getClass().getName()).warning(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<DmcBuildingMaterialEntity> findByImportId(Integer importId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcBuildingMaterialEntity> criteriaQuery = builder.createQuery(DmcBuildingMaterialEntity.class);
        Root<DmcBuildingMaterialEntity> root = criteriaQuery.from(DmcBuildingMaterialEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcBuildingMaterialEntity_.id)));
        predicates.add(builder.equal(root.get(DmcBuildingMaterialEntity_.importId), importId));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcBuildingMaterialEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
