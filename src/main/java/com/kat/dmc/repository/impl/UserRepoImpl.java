package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcUserEntity;
import com.kat.dmc.common.model.DmcUserEntity_;
import com.kat.dmc.repository.interfaces.UserRepo;
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
public class UserRepoImpl implements UserRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public DmcUserEntity getUserByUsernameAndPassword(String username, String md5_password) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcUserEntity> criteriaQuery = builder.createQuery(DmcUserEntity.class);
        Root<DmcUserEntity> root = criteriaQuery.from(DmcUserEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcUserEntity_.id)));
        predicates.add(builder.equal(root.get(DmcUserEntity_.username), username));
        predicates.add(builder.equal(root.get(DmcUserEntity_.password), md5_password));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcUserEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<DmcUserEntity> findAll(){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcUserEntity> criteriaQuery = builder.createQuery(DmcUserEntity.class);
        Root<DmcUserEntity> root = criteriaQuery.from(DmcUserEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcUserEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcUserEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcUserEntity dmcUserEntity) {
        entityManager.merge(dmcUserEntity);
    }

    @Override
    public void delete(DmcUserEntity dmcUserEntity) {
        entityManager.remove(dmcUserEntity);
    }

    @Override
    public DmcUserEntity findById(Integer userId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcUserEntity> criteriaQuery = builder.createQuery(DmcUserEntity.class);
        Root<DmcUserEntity> root = criteriaQuery.from(DmcUserEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcUserEntity_.id), userId));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcUserEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
