package com.kat.dmc.common.util;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utilities for resource folder
 *
 * @author AnhNT
 *
 */
public class ResourceUtil {

    private static Properties prop = new Properties();
    private static Logger logger = LoggerFactory.getLogger(ResourceUtil.class);

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
            prop.load(readFile(path));
            value = prop.getProperty(key);
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
        return value;
    }

}