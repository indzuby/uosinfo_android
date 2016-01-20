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
@ParseClassName("Word")
public class Word extends ParseObject{

    public String getWord() {
        return getString("word");
    }

    public void setWord(String word) {
        put("word",word);
    }

    public College getCollege1() throws ParseException {
        ParseQuery<College> query = ParseQuery.getQuery(College.class);
        query.fromPin();
        return query.get(getParseObject("college1").getObjectId());

    }

    public void setCollege1(College college1) {
        put("college1",college1);
    }
    public College getCollege2() throws ParseException {
        ParseQuery<College> query = ParseQuery.getQuery(College.class);
        query.fromPin();
        return query.get(getParseObject("college2").getObjectId());

    }

    public void setCollege2(College college2) {
        put("college2",college2);
    }

}
