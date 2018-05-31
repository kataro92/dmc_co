package com.kat.dmc.repository.impl;

import com.kat.dmc.repository.interfaces.UtilRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;

@Repository
public class UtilRepoImpl implements UtilRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int findSequenceNextval(String sequenceName) {
        Query query;
        query = entityManager.createNativeQuery("select nextval(?)");
        query.setParameter(1, sequenceName);
        BigInteger nextSeq = (BigInteger) query.getSingleResult();
        return nextSeq.intValue();
    }
}
