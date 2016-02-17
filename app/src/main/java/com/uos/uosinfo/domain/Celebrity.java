package com.uos.uosinfo.domain;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseQuery;

/**
 * Created by 주현 on 2016-01-10.
 */
@ParseClassName("Celebrity")
public class Celebrity extends Common{
    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name",name);
    }

    public String getImage() {
        return getString("image");
    }

    public void setImage(String image) {
        put("image",image);
    }

    public String getBook() {
        return getString("book");
    }

    public void setBook(String book) {
        put("book",book);
    }

    public String getTitleKo() {
        return getString("titleKo");
    }

    public void setTitleKo(String titleKo) {
        put("titleKo",titleKo);
    }

    public String getTitleEn() {
        return getString("titleEn");
    }

    public void setTitleEn(String titleEn) {
        put("titleEn",titleEn);
    }

    public String getWikiKo() {
        return getString("wikiKo");
    }

    public void setWikiKo(String wikiKo) {
        put("wikiKo",wikiKo);
    }

    public String getWikiEn() {
        return getString("wikiEn");
    }

    public void setWikiEn(String wikiEn) {
        put("wikiEn",wikiEn);
    }

    public String getFieldKo() {
        return getString("fieldKo");
    }

    public void setFieldKo(String fieldKo) {
        put("fieldKo",fieldKo);
    }

    public String getFieldEn() {
        return getString("fieldEn");
    }

    public void setFieldEn(String fieldEn) {
        put("fieldEn",fieldEn);
    }

    College college;
    public College getCollege() {
        if(college!=null) return college;
        try{
            ParseQuery<College> query = ParseQuery.getQuery(College.class);
            query.fromPin();
            return (college = query.get(getParseObject("college").getObjectId()));
        }catch (ParseException e){
            e.printStackTrace();
            return null;
        }
    }

    public void setCollege(College college) {
        this.college = college;
        put("college",college);
    }
}
