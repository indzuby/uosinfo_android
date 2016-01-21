package com.uos.uosinfo.domain;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;

/**
 * Created by 주현 on 2016-01-10.
 */
@ParseClassName("PathFinder")
public class PathFinder extends ParseObject {


    public Date getStartDatetime() {
        return getDate("startDatetime");
    }

    public void setStartDatetime(Date startDatetime) {
        put("startDatetime", startDatetime);
    }

    public Date getEndDatetime() {
        return getDate("endDatetime");
    }

    public void setEndDatetime(Date endDatetime) {
        put("endDatetime", endDatetime);
    }

    public Integer getDisplayOrder() {
        return getInt("displayOrder");
    }

    public void setDisplayOrder(Integer displayOrder) {
        put("displayOrder", displayOrder);
    }

    public Celebrity getGreatMan(){
        try {
            ParseQuery<Celebrity> query = ParseQuery.getQuery(Celebrity.class);
            query.fromPin();
            return query.get(getParseObject("celebrity").getObjectId());

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void setGreatMan(Celebrity celebrity) {
        put("celebrity", celebrity);
    }
}
