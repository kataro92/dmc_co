package com.kat.dmc.common.util;

public class StringUtil {
    public static boolean isCode(String input){
        return input.replaceAll("[^A-Z0-9-_]+","").equals(input);
    }
    public static boolean isEmpty(String input){
        return input == null || input.trim().equals("");
    }

    public static String removeDecimalPlace(String input){
        if(isEmpty(input)){
            return null;
        }
        return input.replaceAll(",","");
    }
}
