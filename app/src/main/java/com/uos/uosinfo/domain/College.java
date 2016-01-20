package com.uos.uosinfo.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by user on 2016-01-20.
 */
@ParseClassName("College")
public class College extends ParseObject{

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name",name);
    }

    public String getNameEn() {
        return getString("nameEn");
    }

    public void setNameEn(String nameEn) {
        put("nameEn",nameEn);
    }
    public int getDisplayOrder() {
        return getInt("displayOrder");
    }

    public void setDisplayOrder(Integer displayOrder) {
        put("displayOrder",displayOrder);
    }
}
