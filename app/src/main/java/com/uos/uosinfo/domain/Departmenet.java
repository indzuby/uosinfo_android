package com.uos.uosinfo.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by user on 2016-01-20.
 */
@DatabaseTable(tableName = "department")
public class Departmenet {
    @DatabaseField(columnName = "object_id",id = true)
    String objectId;
    @DatabaseField
    Integer number;
    @DatabaseField
    String name;
    @DatabaseField
    String introduction;
    @DatabaseField
    String rightPeople;
    @DatabaseField
    String phone;
    @DatabaseField
    String url;
    @DatabaseField(columnName = "college",foreign = true)
    College college;
    @DatabaseField(columnName = "create_datetime")
    Date createDatetime;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getRightPeople() {
        return rightPeople;
    }

    public void setRightPeople(String rightPeople) {
        this.rightPeople = rightPeople;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }
}
