package com.kat.dmc.controller;

import com.kat.dmc.common.model.LoggedUser;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("authority")
@SessionScoped
public class AuthorityController implements Serializable {

    public AuthorityController(){}

    private LoggedUser loggedUser;

    public LoggedUser getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(LoggedUser loggedUser) {
        this.loggedUser = loggedUser;
    }
}
