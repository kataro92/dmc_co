package com.kat.dmc.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class CommonUtil {
    public static boolean isEmpty(String args){
        return args == null || args.trim().equals("") ? true : false;
    }
    public static boolean isEmpty(String[] args){
        return args == null || args.length == 0 ? true : false;
    }
    public static boolean isEmpty(Object[] args){
        return args == null || args.length == 0 ? true : false;
    }
    public static boolean isEmpty(List args){
        return args == null || args.isEmpty() ? true : false;
    }
    public static boolean isEmpty(ArrayList args){
        return args == null || args.isEmpty() ? true : false;
    }

    public static boolean isEmpty(Object args){
        if(args == null){
            return true;
        }
        if(args.getClass().equals(String.class)){
            return isEmpty((String) args);
        }
        if(args.getClass().equals(String[].class)){
            return isEmpty((String[]) args);
        }
        if(args.getClass().equals(Object[].class)){
            return isEmpty((Object[]) args);
        }
        if(args.getClass().equals(List.class)){
            return isEmpty((List) args);
        }
        if(args.getClass().equals(ArrayList.class)){
            return isEmpty((ArrayList) args);
        }
        return false;
    }


    public static String rfc5987_encode(final String s) throws UnsupportedEncodingException {
        final byte[] s_bytes = s.getBytes("UTF-8");
        final int len = s_bytes.length;
        final StringBuilder sb = new StringBuilder(len << 1);
        final char[] digits = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        final byte[] attr_char = {'!','#','$','&','+','-','.','0','1','2','3','4','5','6','7','8','9',
                'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','^','_','`',
                'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','|', '~'};
        for (int i = 0; i < len; ++i) {
            final byte b = s_bytes[i];
            if (Arrays.binarySearch(attr_char, b) >= 0)
                sb.append((char) b);
            else {
                sb.append('%');
                sb.append(digits[0x0f & (b >>> 4)]);
                sb.append(digits[b & 0x0f]);
            }
        }

        return sb.toString();
    }

    public static String makeJapanEncodeOnly(String japanString) throws UnsupportedEncodingException {
        StringBuilder rt = new StringBuilder("");
        for (char ch: japanString.toCharArray()) {
            if(isJapanese(ch)){
                rt.append(URLEncoder.encode(CommonUtil.getObjectString(ch),"UTF-8"));
            }else{
                rt.append(ch);
            }
        }
        return rt.toString();
    }

    public static boolean isJapanese(char c) {
        Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
        return japaneseBlocks.contains(block);
    }

    public final static Set japaneseBlocks = new HashSet();
    static {
        japaneseBlocks.add(Character.UnicodeBlock.KATAKANA);
        japaneseBlocks.add(Character.UnicodeBlock.HIRAGANA);
        japaneseBlocks.add(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS);
        japaneseBlocks.add(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A);
        // add other blocks as necessary
    }

    public static String getObjectString(Object inputObj){
        if(inputObj == null){
            return null;
        }else{
            return String.valueOf(inputObj);
        }
    }
}
