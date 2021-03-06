package com.kat.dmc.controller;

import com.kat.dmc.service.interfaces.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;

@Named("home")
@ConversationScoped
public class HomeController implements Serializable {
    private String currentPage;
    private String currentTemplate;

    @Inject AuthorityController authorityController;

    @Autowired AuthorityService authorityService;

    @PostConstruct
    private void init(){
        currentPage = "/admin/login.xhtml";
    }

    public void selectPage(String page) throws IOException {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        String sessionId = session.getId();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/" + page + "?JSESSIONID=" + sessionId);
//        FacesContext.getCurrentInstance().getExternalContext().dispatch("/" + page);
    }

    public void checkAuthenicate() throws IOException {
        if(authorityController.getLoggedUser() == null){
            FacesContext.getCurrentInstance().getExternalContext().dispatch("/login");
        }else{
            FacesContext.getCurrentInstance().getExternalContext().dispatch("/main");
            currentPage = "/pages/dash.xhtml";
        }
    }

    public void checkLoggedAuthenicate() throws IOException {
        if(authorityController.getLoggedUser() == null){
            FacesContext.getCurrentInstance().getExternalContext().dispatch("/login");
        }
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getCurrentTemplate() {
        if(currentTemplate == null){
            currentTemplate = "default";
        }
        return currentTemplate;
    }

    public void setCurrentTemplate(String currentTemplate) {
        this.currentTemplate = currentTemplate;
    }
}
