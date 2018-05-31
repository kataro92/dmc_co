package com.kat.dmc.common.util;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourcesUtil {
    private static Properties prop;
    private static Logger logger = LoggerFactory.getLogger(ResourcesUtil.class);

    private static InputStream readFile(String path) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        return loader.getResourceAsStream(path);
    }

    public static String readShiftUtf8File(String path) {
        String content = "";
        try {
            content = IOUtils.toString(readFile(path), "UTF-8");
        } catch (IOException e) {
            logger.error("Error: ", e);
        }
        return content;
    }
    public static String readShiftJsFile(String path) {
        String content = "";
        try {
            content = IOUtils.toString(readFile(path), "SHIFT-JIS");
        } catch (IOException e) {
            logger.error("Error: ", e);
        }
        return content;
    }

    public static String getProperty(String path, String key) {
        String value = "";
        try {
            getProp().load(readFile(path));
            value = getProp().getProperty(key);
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
        return value;
    }

    public static Properties getProp() {
        if(prop == null){
            prop = new Properties();
        }
        return prop;
    }
}
