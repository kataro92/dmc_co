package com.kat.dmc.controller;

import com.kat.dmc.common.constant.CommonConst;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("common")
@ConversationScoped
public class CommonController implements Serializable{

    @PostConstruct
    private void init(){

    }

    public String getCommon(String type, String value){
        CommonConst.Code code = CommonConst.Code.valueOf(type + "_" + value);
        return code.value();
    }
}
