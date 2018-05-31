package com.kat.dmc.common.util;

public class CommonUtil {
    public static boolean isNull(String args){
        return args == null || args.equals("") ? true : false;
    }
}
