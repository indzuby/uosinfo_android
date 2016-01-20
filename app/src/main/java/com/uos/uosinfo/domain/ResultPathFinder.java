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
@ParseClassName("ResultPathFinder")
public class ResultPathFinder extends ParseObject{
    @DatabaseField(columnName = "great_man",foreign = true)
    GreatMan greatMan;

    public GreatMan getGreatMan() throws ParseException {
        ParseQuery<GreatMan> query = ParseQuery.getQuery(GreatMan.class);
        query.fromPin();
        return query.get(getParseObject("greatMan").getObjectId());

    }

    public void setGreatMan(GreatMan greatMan) {
        put("greatMan",greatMan);
    }
}
