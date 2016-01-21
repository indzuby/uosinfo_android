package com.uos.uosinfo.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;

/**
 * Created by user on 2016-01-20.
 */
@ParseClassName("Department")
public class Department extends ParseObject {

    public Integer getNumber() {
        return getInt("number");
    }

    public void setNumber(Integer number) {
        put("number", number);
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public String getIntroduction() {
        return getString("introduction");
    }

    public void setIntroduction(String introduction) {
        put("introduction", introduction);
    }

    public String getRightPeople() {
        return getString("rightPeople");
    }

    public void setRightPeople(String rightPeople) {
        put("rightPeople", rightPeople);
    }

    public String getPhone() {
        return getString("phone");
    }

    public void setPhone(String phone) {
        put("phone", phone);
    }

    public String getUrl() {
        return getString("url");
    }

    public void setUrl(String url) {
        put("url", url);
    }


    public College getCollege() {
        try {
            ParseQuery<College> query = ParseQuery.getQuery(College.class);
            query.fromPin();
            return query.get(getParseObject("college").getObjectId());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setCollege(College college) {
        put("college", college);
    }
}
