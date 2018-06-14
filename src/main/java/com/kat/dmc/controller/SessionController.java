package com.kat.dmc.controller;


import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("session")
@SessionScoped
public class SessionController implements Serializable {

    public static int CODE_COL_NUM;
    public static int QUANTITY_COL_NUM;
    public static int PRICE_COL_NUM;

    @PostConstruct
    public void init(){
        CODE_COL_NUM = 4;
        QUANTITY_COL_NUM = 7;
        PRICE_COL_NUM = 8;
    }
}
