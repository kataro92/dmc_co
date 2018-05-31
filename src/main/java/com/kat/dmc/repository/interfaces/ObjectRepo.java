package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcObjectEntity;
import com.kat.dmc.common.model.DmcUserObjectEntity;

import java.util.List;

public interface ObjectRepo {
    List<DmcObjectEntity> findAll();
    void save(DmcObjectEntity userEntity);
    void delete(DmcObjectEntity userEntity);
    DmcObjectEntity findById(Integer objectId);
    DmcObjectEntity findByParentId(Integer objectId);
    void removeAllPermissionByUserID(Integer userId);
    void saveUserObject(DmcUserObjectEntity savingObj);
    List<Integer> findObjectIdByUserId(Integer userId);
}
