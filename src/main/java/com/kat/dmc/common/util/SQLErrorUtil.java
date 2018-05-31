package com.kat.dmc.common.util;

import com.kat.dmc.common.constant.SQLErrorConst;

public class SQLErrorUtil {
    public static String getSQLError(String exMessage){
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
