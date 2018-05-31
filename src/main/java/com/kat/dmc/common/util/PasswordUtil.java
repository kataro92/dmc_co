package com.kat.dmc.common.util;

import java.security.MessageDigest;


public class PasswordUtil {

    public static void main(String[] args){
        System.out.println(hashMD5("admin"));
    }

    public static String hashMD5(String password){
        password += ResourcesUtil.getProperty("system.properties","md5_seed");
        String strRt = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< digest.length ;i++)
            {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            strRt = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return strRt;
        }
    }
}
