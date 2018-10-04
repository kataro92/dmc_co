package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcObjectEntity;
import com.kat.dmc.common.model.DmcObjectEntity_;
import com.kat.dmc.common.model.DmcUserObjectEntity;
import com.kat.dmc.common.model.DmcUserObjectEntity_;
import com.kat.dmc.repository.interfaces.ObjectRepo;
import com.kat.dmc.repository.interfaces.UtilRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ObjectRepoImpl implements ObjectRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;

    @Override
    public List<DmcObjectEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcObjectEntity> criteriaQuery = builder.createQuery(DmcObjectEntity.class);
        Root<DmcObjectEntity> root = criteriaQuery.from(DmcObjectEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcObjectEntity_.objectId)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcObjectEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcObjectEntity userEntity) {
//        userEntity.setLastModified(new Timestamp(new Date().getTime()));
        entityManager.merge(userEntity);

    }

    @Override
    public void delete(DmcObjectEntity userEntity) {
        entityManager.remove(userEntity);

    }

    @Override
    public DmcObjectEntity findById(Integer objectId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcObjectEntity> criteriaQuery = builder.createQuery(DmcObjectEntity.class);
        Root<DmcObjectEntity> root = criteriaQuery.from(DmcObjectEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcObjectEntity_.objectId), objectId));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcObjectEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            throw new RuntimeException("Single return empty result !");
        }
    }

    @Override
    public DmcObjectEntity findByParentId(Integer objectParentId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcObjectEntity> criteriaQuery = builder.createQuery(DmcObjectEntity.class);
        Root<DmcObjectEntity> root = criteriaQuery.from(DmcObjectEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcObjectEntity_.parentObjectId), objectParentId));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcObjectEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            throw new RuntimeException("Single return empty result !");
        }
    }

    @Override
    public void removeAllPermissionByUserID(Integer userId) {
        Query query = entityManager.createNativeQuery("DELETE FROM dmc_user_object WHERE user_id = ?1")
                .setParameter("1", userId);
        query.executeUpdate();

    }

    @Override
    public void saveUserObject(DmcUserObjectEntity savingObj) {
        savingObj.setUserObjectId(utilRepo.findSequenceNextval("dmc_user_object_user_object_id_seq"));
        entityManager.persist(savingObj);
    }

    @Override
    public List<Integer> findObjectIdByUserId(Integer userId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery();
        Root<DmcUserObjectEntity> root = criteriaQuery.from(DmcUserObjectEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcUserObjectEntity_.userId), userId));
        criteriaQuery.select(root.get(DmcUserObjectEntity_.objectId))
                .where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<Integer> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
