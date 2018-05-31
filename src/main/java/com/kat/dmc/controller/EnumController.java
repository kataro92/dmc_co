package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("enumBox")
@ApplicationScoped
public class EnumController {

    public String getState(String key){
        ControllerAction.State[] values = ControllerAction.State.values();
        for(ControllerAction.State state : values){
            if(key.equals(state.name())){
                return state.toString();
            }
        }
        return "";
    }
}
