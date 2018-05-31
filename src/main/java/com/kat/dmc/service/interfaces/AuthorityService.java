package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.model.LoggedUser;

public interface AuthorityService {
    LoggedUser validateUser(String username, String password);
}
