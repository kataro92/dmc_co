package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.DmcUserEntity;

import java.util.List;

public interface UserRepo {
    DmcUserEntity getUserByUsernameAndPassword(String username, String md5_password);
    List<DmcUserEntity> findAll();
    void save(DmcUserEntity dmcUserEntity);
    void delete(DmcUserEntity dmcUserEntity);
    DmcUserEntity findById(Integer userId);
}
