package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcDocumentEntity;

import javax.persistence.NoResultException;
import java.util.List;

public interface DocumentRepo {
    List<DmcDocumentEntity> findAll();
    List<DmcDocumentEntity> findAllByType(String type);
    void save(DmcDocumentEntity userEntity);
    void delete(DmcDocumentEntity userEntity);
    DmcDocumentEntity findOneByCode(String code) throws NoResultException;
    List<DmcDocumentEntity> findAllByTypeAndParentId(String type, int id);
    void deleteByEmpIdNotIn(List<Integer> lstDocIds, int empId);
}
