package com.kat.dmc.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public static Date toDate(String input, String format) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.parse(input);
    }
}
