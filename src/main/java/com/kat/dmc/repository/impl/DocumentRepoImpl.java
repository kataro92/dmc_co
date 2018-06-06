package com.kat.dmc.repository.impl;

import com.kat.dmc.common.model.DmcDocumentEntity;
import com.kat.dmc.common.model.DmcDocumentEntity;
import com.kat.dmc.common.model.DmcDocumentEntity_;
import com.kat.dmc.repository.interfaces.DocumentRepo;
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
public class DocumentRepoImpl implements DocumentRepo {


    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UtilRepo utilRepo;
    
    @Override
    public List<DmcDocumentEntity> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcDocumentEntity> criteriaQuery = builder.createQuery(DmcDocumentEntity.class);
        Root<DmcDocumentEntity> root = criteriaQuery.from(DmcDocumentEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.isNotNull(root.get(DmcDocumentEntity_.id)));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcDocumentEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<DmcDocumentEntity> findAllByType(String type) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcDocumentEntity> criteriaQuery = builder.createQuery(DmcDocumentEntity.class);
        Root<DmcDocumentEntity> root = criteriaQuery.from(DmcDocumentEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcDocumentEntity_.type),type));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcDocumentEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void save(DmcDocumentEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(DmcDocumentEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public DmcDocumentEntity findOneByCode(String code) throws NoResultException {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcDocumentEntity> criteriaQuery = builder.createQuery(DmcDocumentEntity.class);
        Root<DmcDocumentEntity> root = criteriaQuery.from(DmcDocumentEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcDocumentEntity_.code),code));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcDocumentEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<DmcDocumentEntity> findAllByTypeAndParentId(String type, int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DmcDocumentEntity> criteriaQuery = builder.createQuery(DmcDocumentEntity.class);
        Root<DmcDocumentEntity> root = criteriaQuery.from(DmcDocumentEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get(DmcDocumentEntity_.type),type));
        predicates.add(builder.equal(root.get(DmcDocumentEntity_.parentId),id));
        criteriaQuery.select(root).where(predicates.stream().toArray(Predicate[]::new));
        final TypedQuery<DmcDocumentEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void deleteByEmpIdNotIn(List<Integer> lstDocIds, int empId) {
        String lstEmp = "-1";
        for(Integer id : lstDocIds){
            lstEmp += ","+id;
        }
        Query query = entityManager.createNativeQuery("DELETE FROM dmc_document " +
                "WHERE id = ?1 AND type='document' AND parent_id NOT IN (" +
                lstEmp +
                ")")
                .setParameter("1", empId);
        query.executeUpdate();
    }
}
