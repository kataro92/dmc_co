package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.UserEntity;

import java.util.List;

public interface UserRepo {
    UserEntity getUserByUsernameAndPassword(String username, String md5_password);
    List<UserEntity> findAll();
    void save(UserEntity userEntity);
    void delete(UserEntity userEntity);
    UserEntity findById(Integer userId);
}
