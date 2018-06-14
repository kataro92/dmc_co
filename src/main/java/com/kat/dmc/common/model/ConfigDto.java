package com.kat.dmc.common.model;

import java.io.Serializable;

public class ConfigDto implements Serializable {

    private int id;
    private String key;
    private String value;
    private String ext;

    public ConfigDto() {
    }

    public ConfigDto(int id, String key, String value, String ext) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.ext = ext;
    }

    @Override
    public ConfigDto clone(){
        return new ConfigDto(id, key, value, ext);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
