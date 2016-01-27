package com.uos.uosinfo.domain;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by user on 2016-01-20.
 */
@ParseClassName("Word")
public class Word extends ParseObject {

    public String getWord() {
        return getString("word");
    }

    public void setWord(String word) {
        put("word", word);
    }

    College college1;
    public College getCollege1() {
        if(college1 !=null)
            return college1;
        try {
            ParseQuery<College> query = ParseQuery.getQuery(College.class);
            query.fromPin();
            return (college1 = query.get(getParseObject("college1").getObjectId()));

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setCollege1(College college1) {
        put("college1", college1);
    }

    College college2 ;
    public College getCollege2(){
        if(college2!=null)
            return college2;
        try {
            ParseQuery<College> query = ParseQuery.getQuery(College.class);
            query.fromPin();
            return (college2 = query.get(getParseObject("college2").getObjectId()));

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setCollege2(College college2) {
        put("college2", college2);
    }

}
