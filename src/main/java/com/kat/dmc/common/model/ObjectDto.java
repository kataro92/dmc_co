package com.kat.dmc.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ObjectDto implements Serializable {
    private int objectId;
    private String objectTitle;
    private int parentObjectId;
    private String objectValue;
    private String objectType;
    private String objectIcon;
    private List<ObjectDto> lstChildObject;
    private boolean hasPermission;
    private int ord;

    public ObjectDto() {
    }

    public ObjectDto(int objectId, String objectTitle, int parentObjectId,
                     String objectValue, String objectType, List<ObjectDto> lstChildObject,
                     boolean hasPermission, String objectIcon, int ord) {
        this.objectId = objectId;
        this.objectTitle = objectTitle;
        this.parentObjectId = parentObjectId;
        this.objectValue = objectValue;
        this.objectType = objectType;
        if(lstChildObject != null){
            List<ObjectDto> lstCopyChildObject = new ArrayList<>();
            for(ObjectDto objectDto : lstChildObject){
                lstCopyChildObject.add(objectDto.clone());
            }
            this.lstChildObject = lstCopyChildObject;
        }
        this.hasPermission = hasPermission;
        this.objectIcon = objectIcon;
        this.ord = ord;
    }

    @Override
    public ObjectDto clone(){
        return new ObjectDto(this.objectId, this.objectTitle, this.parentObjectId,
                this.objectValue, this.objectType, this.lstChildObject, this.hasPermission, this.objectIcon, this.ord);
    }
    public ObjectDto cloneParent(){
        return new ObjectDto(this.objectId, this.objectTitle, this.parentObjectId,
                this.objectValue, this.objectType, null, this.hasPermission, this.objectIcon, this.ord);
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getObjectTitle() {
        return objectTitle;
    }

    public void setObjectTitle(String objectTitle) {
        this.objectTitle = objectTitle;
    }

    public int getParentObjectId() {
        return parentObjectId;
    }

    public void setParentObjectId(int parentObjectId) {
        this.parentObjectId = parentObjectId;
    }

    public String getObjectValue() {
        return objectValue;
    }

    public void setObjectValue(String objectValue) {
        this.objectValue = objectValue;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public List<ObjectDto> getLstChildObject() {
        return lstChildObject;
    }

    public void setLstChildObject(List<ObjectDto> lstChildObject) {
        this.lstChildObject = lstChildObject;
    }

    public boolean isHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(boolean hasPermission) {
        this.hasPermission = hasPermission;
    }

    public String getObjectIcon() {
        return objectIcon;
    }

    public void setObjectIcon(String objectIcon) {
        this.objectIcon = objectIcon;
    }

    public int getOrd() {
        return ord;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }
}
