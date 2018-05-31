package com.kat.dmc.common.model;

import javax.persistence.*;

@Entity
@Table(name = "dmc_user_object", schema = "public", catalog = "dmco_sys")
public class DmcUserObjectEntity {
    private int userObjectId;
    private int userId;
    private int objectId;

    @Id
    @Column(name = "user_object_id")
    public int getUserObjectId() {
        return userObjectId;
    }

    public void setUserObjectId(int userObjectId) {
        this.userObjectId = userObjectId;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "object_id")
    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DmcUserObjectEntity that = (DmcUserObjectEntity) o;

        if (userObjectId != that.userObjectId) return false;
        if (userId != that.userId) return false;
        if (objectId != that.objectId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userObjectId;
        result = 31 * result + userId;
        result = 31 * result + objectId;
        return result;
    }
}
