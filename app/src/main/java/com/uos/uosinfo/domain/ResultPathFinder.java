package com.uos.uosinfo.domain;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by user on 2016-01-20.
 */
@ParseClassName("ResultPathFinder")
public class ResultPathFinder extends ParseObject {
    Celebrity celebrity;
    public Celebrity getCelebrity() {
        if(celebrity !=null)
            return celebrity;
        try {
            ParseQuery<Celebrity> query = ParseQuery.getQuery(Celebrity.class);
            query.fromPin();
            return (celebrity = query.get(getParseObject("celebrity").getObjectId()));

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void setCelebrity(Celebrity celebrity) {
        put("celebrity", celebrity);
    }
}
