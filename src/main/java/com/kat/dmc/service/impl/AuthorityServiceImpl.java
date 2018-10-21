package com.kat.dmc.service.impl;

import com.kat.dmc.common.constant.UserTypeConst;
import com.kat.dmc.common.dto.LoggedUser;
import com.kat.dmc.common.model.DmcEmployeeEntity;
import com.kat.dmc.common.util.PasswordUtil;
import com.kat.dmc.repository.interfaces.EmployeeRepo;
import com.kat.dmc.service.interfaces.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public LoggedUser validateUser(String username, String password) {
        String md5Password = PasswordUtil.hashMD5(password);
        DmcEmployeeEntity userByUsernameAndPassword =
                employeeRepo.getEmployeeByEmployeenameAndPassword(username.toLowerCase(), md5Password);
        if(userByUsernameAndPassword != null) {
            LoggedUser loggedUser = new LoggedUser();
            loggedUser.setUsername(username);
            loggedUser.setUserId(userByUsernameAndPassword.getId());
            if(userByUsernameAndPassword.getUserType() == UserTypeConst.ADMIN
                    || userByUsernameAndPassword.getUserType() == UserTypeConst.NORMAL){
                loggedUser.setUserType(userByUsernameAndPassword.getUserType());
            }
            if(userByUsernameAndPassword.getUserType() == UserTypeConst.NORMAL){

            }
            loggedUser.setEmpName(userByUsernameAndPassword.getName());
            return loggedUser;
        }
        return null;
    }
}
