package com.kat.dmc.service.impl;

import com.kat.dmc.common.constant.UserTypeConst;
import com.kat.dmc.common.model.LoggedUser;
import com.kat.dmc.common.model.UserEntity;
import com.kat.dmc.common.util.PasswordUtil;
import com.kat.dmc.repository.interfaces.UserRepo;
import com.kat.dmc.service.interfaces.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    UserRepo userRepo;

    @Override
    public LoggedUser validateUser(String username, String password) {
        String md5Password = PasswordUtil.hashMD5(password);
        UserEntity userByUsernameAndPassword = userRepo.getUserByUsernameAndPassword(username.toLowerCase(), md5Password);
        if(userByUsernameAndPassword != null) {
            LoggedUser loggedUser = new LoggedUser();
            loggedUser.setUsername(username);
            loggedUser.setUserId(userByUsernameAndPassword.getId());
            if(userByUsernameAndPassword.getPermission() == UserTypeConst.ADMIN || userByUsernameAndPassword.getPermission() == UserTypeConst.NORMAL){
                loggedUser.setUserType(userByUsernameAndPassword.getPermission());
            }
            if(userByUsernameAndPassword.getPermission() == UserTypeConst.NORMAL){

            }
            loggedUser.setEmpName(userByUsernameAndPassword.getName());
            return loggedUser;
        }
        return null;
    }
}
