package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.dto.LoggedUser;

public interface AuthorityService {
    LoggedUser validateUser(String username, String password);
}
