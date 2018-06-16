package com.kat.dmc.common.model;

import javax.persistence.*;

@Entity
@Table(name = "dmc_object", schema = "public")
public class DmcObjectEntity {
    private int objectId;
    private String objectTitle;
    private int parentObjectId;
    private String objectValue;
    private String objectType;
    private String objectIcon;
    private int ord;

    @Id
    @Column(name = "object_id")
    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    @Basic
    @Column(name = "object_title")
    public String getObjectTitle() {
        return objectTitle;
    }

    public void setObjectTitle(String objectTitle) {
        this.objectTitle = objectTitle;
    }

    @Basic
    @Column(name = "parent_object_id")
    public int getParentObjectId() {
        return parentObjectId;
    }

    public void setParentObjectId(int parentObjectId) {
        this.parentObjectId = parentObjectId;
    }

    @Basic
    @Column(name = "object_value")
    public String getObjectValue() {
        return objectValue;
    }

    public void setObjectValue(String objectValue) {
        this.objectValue = objectValue;
    }

    @Basic
    @Column(name = "object_type")
    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    @Basic
    @Column(name = "ord")
    public int getOrd() {
        return ord;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DmcObjectEntity that = (DmcObjectEntity) o;

        if (objectId != that.objectId) return false;
        if (parentObjectId != that.parentObjectId) return false;
        if (objectTitle != null ? !objectTitle.equals(that.objectTitle) : that.objectTitle != null) return false;
        if (objectValue != null ? !objectValue.equals(that.objectValue) : that.objectValue != null) return false;
        if (objectType != null ? !objectType.equals(that.objectType) : that.objectType != null) return false;
        if (objectIcon != null ? !objectIcon.equals(that.objectIcon) : that.objectIcon != null) return false;
        if (ord != that.ord) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = objectId;
        result = 31 * result + (objectTitle != null ? objectTitle.hashCode() : 0);
        result = 31 * result + parentObjectId;
        result = 31 * result + (objectValue != null ? objectValue.hashCode() : 0);
        result = 31 * result + (objectType != null ? objectType.hashCode() : 0);
        result = 31 * result + (objectIcon != null ? objectIcon.hashCode() : 0);
        result = 31 * result + ord;
        return result;
    }

    @Basic
    @Column(name = "object_icon")
    public String getObjectIcon() {
        return objectIcon;
    }

    public void setObjectIcon(String objectIcon) {
        this.objectIcon = objectIcon;
    }
}
