package com.kat.dmc.common.util;

import com.kat.dmc.common.constant.SQLErrorConst;

public class SQLErrorUtil {
    public static String getSQLError(Exception ex){
        if(ex == null){
            return "500";
        }
        String exMessage = ex.getMessage();
        if(exMessage == null){
            return "500";
        }
        SQLErrorConst.Error[] values = SQLErrorConst.Error.values();
        for(SQLErrorConst.Error error : values){
            if(exMessage.contains("'"+error.error()+"'")
        || exMessage.contains("["+error.error()+"]")){
                return error.message();
            }
        }
        return "500";
    }
}
