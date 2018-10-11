package com.kat.dmc.repository.impl;

import com.kat.dmc.common.dto.PropertyDto;
import com.kat.dmc.common.model.DmcPropertyEntity;
import com.kat.dmc.common.model.DmcPropertyEntity_;
import com.kat.dmc.repository.interfaces.PropertyRepo;
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
import java.util.Objects;
import java.util.logging.Logger;

@Repository
public class PropertyRepoImpl implements PropertyRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DmcPropertyEntity> findAll(){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcPropertyEntity> criteriaQuery = builder.createQuery(DmcPropertyEntity.class);
        Root<DmcPropertyEntity> root = criteriaQuery.from(DmcPropertyEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcPropertyEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcPropertyEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcPropertyEntity dmcPropertyEntity) {
        entityManager.merge(dmcPropertyEntity);
    }

    @Override
    public void delete(DmcPropertyEntity dmcPropertyEntity) {
        entityManager.remove(dmcPropertyEntity);
    }

    @Override
    public DmcPropertyEntity findById(Integer propertyId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcPropertyEntity> criteriaQuery = builder.createQuery(DmcPropertyEntity.class);
        Root<DmcPropertyEntity> root = criteriaQuery.from(DmcPropertyEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcPropertyEntity_.id), propertyId));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcPropertyEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            Logger.getLogger(this.getClass().getName()).warning(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<DmcPropertyEntity> findAllByReq(PropertyDto searchProperty) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcPropertyEntity> criteriaQuery = builder.createQuery(DmcPropertyEntity.class);
        Root<DmcPropertyEntity> root = criteriaQuery.from(DmcPropertyEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcPropertyEntity_.id)));
        predicates.add(builder.like(root.get(DmcPropertyEntity_.name), "%" + Objects.toString(searchProperty.getName(), "") + "%"));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcPropertyEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
