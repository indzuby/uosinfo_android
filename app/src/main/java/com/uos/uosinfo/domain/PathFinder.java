package com.uos.uosinfo.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;

/**
 * Created by 주현 on 2016-01-10.
 */
@ParseClassName("PathFinder")
public class PathFinder extends ParseObject{


    public Date getStartDatetime() {
        return getDate("startDatetime");
    }

    public void setStartDatetime(Date startDatetime) {
        put("startDatetime",startDatetime);
    }

    public Date getEndDatetime() {
        return getDate("endDatetime");
    }

    public void setEndDatetime(Date endDatetime) {
        put("endDatetime",endDatetime);
    }

    public Integer getDisplayOrder() {
        return getInt("displayOrder");
    }

    public void setDisplayOrder(Integer displayOrder) {
        put("displayOrder",displayOrder);
    }

    public GreatMan getGreatMan() throws ParseException{
        ParseQuery<GreatMan> query = ParseQuery.getQuery(GreatMan.class);
        query.fromPin();
        return query.get(getParseObject("greatMan").getObjectId());

    }

    public void setGreatMan(GreatMan greatMan) {
        put("greatMan",greatMan);
    }
}
