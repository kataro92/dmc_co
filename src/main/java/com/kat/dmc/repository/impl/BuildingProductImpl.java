package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcBuildingProductEntity;
import com.kat.dmc.common.model.DmcBuildingProductEntity_;
import com.kat.dmc.repository.interfaces.BuildingProductRepo;
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
import java.util.ArrayList;
import java.util.List;

@Repository
public class BuildingProductImpl implements BuildingProductRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcBuildingProductEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcBuildingProductEntity> criteriaQuery = builder.createQuery(DmcBuildingProductEntity.class);
        Root<DmcBuildingProductEntity> root = criteriaQuery.from(DmcBuildingProductEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcBuildingProductEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcBuildingProductEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public DmcBuildingProductEntity save(DmcBuildingProductEntity userEntity) {
        return entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcBuildingProductEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcBuildingProductEntity findById(Integer userId) {
        return null;
    }
}
