package com.kat.dmc.controller;


import com.kat.dmc.common.model.LoggedUser;
import com.kat.dmc.common.model.ObjectDto;
import com.kat.dmc.common.util.RescusiveUtil;
import com.kat.dmc.service.interfaces.AuthorityService;
import com.kat.dmc.service.interfaces.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Named("login")
@ConversationScoped
public class LoginController implements Serializable {

    private String txtUsername, txtPassword;
    private boolean remember;

    @Inject
    AuthorityController authorityController;

    @Autowired AuthorityService authorityService;
    @Autowired ObjectService objectService;

    public void actLogin() throws IOException {
        LoggedUser loggedUser = authorityService.validateUser(getTxtUsername(), getTxtPassword());
        if(loggedUser == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authenticate error!", "Wrong password."));
        }else{
            List<ObjectDto> lstObject = objectService.findAll();
            List<Integer> objectIDHasPermission = objectService.findObjectIdByUserId(loggedUser.getUserId());
            List<ObjectDto> lstPermission = new ArrayList<>();
            lstObject.forEach(objectDto -> lstPermission.add(objectDto.clone()));
            lstObject.sort(Comparator.comparingInt(ObjectDto::getOrd));
            RescusiveUtil.rescusiveSetPermission(lstPermission, objectIDHasPermission);
            loggedUser.setLstPermission(lstPermission);
            authorityController.setLoggedUser(loggedUser);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/");
//            FacesContext.getCurrentInstance().getExternalContext().dispatch("/main");
        }
    }

    public void actClear(){
        txtUsername = null;
        txtPassword = null;
        remember = false;
    }

    public void actLogout() throws IOException {
        authorityController.setLoggedUser(null);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/logout");
    }

    public String getTxtUsername() {
        return txtUsername;
    }

    public void setTxtUsername(String txtUsername) {
        this.txtUsername = txtUsername;
    }

    public String getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(String txtPassword) {
        this.txtPassword = txtPassword;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
