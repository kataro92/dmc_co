package com.kat.dmc.controller;

import com.kat.dmc.common.constant.CommonConst;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("common")
@ApplicationScoped
public class CommonController {

    public String getCommon(String type, String code){
        CommonConst.Code[] values = CommonConst.Code.values();
        for(CommonConst.Code eCode : values){
            if(eCode.code().equals(code) && eCode.type().equals(type)){
                return eCode.value();
            }
        }
        return "";
    }
}
