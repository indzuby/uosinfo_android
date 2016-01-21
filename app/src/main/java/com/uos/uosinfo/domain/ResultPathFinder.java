package com.uos.uosinfo.domain;

import com.j256.ormlite.field.DatabaseField;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by user on 2016-01-20.
 */
@ParseClassName("ResultPathFinder")
public class ResultPathFinder extends ParseObject {
    public Celebrity getCelebrity() {
        try {
            ParseQuery<Celebrity> query = ParseQuery.getQuery(Celebrity.class);
            query.fromPin();
            return query.get(getParseObject("celebrity").getObjectId());

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void setCelebrity(Celebrity celebrity) {
        put("celebrity", celebrity);
    }
}
