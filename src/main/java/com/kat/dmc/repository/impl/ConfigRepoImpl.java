package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcConfigEntity;
import com.kat.dmc.common.model.DmcConfigEntity_;
import com.kat.dmc.repository.interfaces.ConfigRepo;
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
public class ConfigRepoImpl implements ConfigRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcConfigEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcConfigEntity> criteriaQuery = builder.createQuery(DmcConfigEntity.class);
        Root<DmcConfigEntity> root = criteriaQuery.from(DmcConfigEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcConfigEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcConfigEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcConfigEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcConfigEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcConfigEntity findById(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcConfigEntity> criteriaQuery = builder.createQuery(DmcConfigEntity.class);
        Root<DmcConfigEntity> root = criteriaQuery.from(DmcConfigEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcConfigEntity_.id), id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcConfigEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            Logger.getLogger(this.getClass().getName()).warning(ex.getMessage());
            return null;
        }
    }

    @Override
    public DmcConfigEntity findByKey(String key) throws NoResultException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcConfigEntity> criteriaQuery = builder.createQuery(DmcConfigEntity.class);
        Root<DmcConfigEntity> root = criteriaQuery.from(DmcConfigEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcConfigEntity_.key), key));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcConfigEntity> query = entityManager.createQuery(criteriaQuery);
        try {
            return query.getSingleResult();
        }catch (NoResultException ex){
            Logger.getLogger(this.getClass().getName()).warning(ex.getMessage());
            return null;
        }
    }

    @Override
    public void saveConfig(String key, String value) {
        DmcConfigEntity configEntity;
        try {
            configEntity = findByKey(key);
        }catch (NoResultException ex){
            configEntity = new DmcConfigEntity();
            configEntity.setId(utilRepo.findSequenceNextval("dmc_config_id_seq"));
            configEntity.setKey(key);
            configEntity.setValue(value);
        }
        configEntity.setValue(value);
        save(configEntity);
    }
}
