package com.uos.uosinfo.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by user on 2016-01-20.
 */
@DatabaseTable(tableName = "college")
public class College {
    @DatabaseField(columnName = "object_id",id = true)
    String objectId;
    @DatabaseField
    String name;
    @DatabaseField(columnName = "name_en")
    String nameEn;
    @DatabaseField(columnName = "create_datetime")
    Date createDatetime;
    @DatabaseField(columnName = "display_order",defaultValue = "0")
    Integer displayOrder;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
}
