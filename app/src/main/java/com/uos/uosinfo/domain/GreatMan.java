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
@ParseClassName("GreatMan")
public class GreatMan extends ParseObject{
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
        return getString("fieldKo");
    }

    public void setFieldEn(String fieldEn) {
        put("fieldEn",fieldEn);
    }

    public College getCollege() throws ParseException {
        ParseQuery<College> query = ParseQuery.getQuery(College.class);
        query.fromPin();
        return query.get(getParseObject("college").getObjectId());
    }

    public void setCollege(College college) {
        put("college",college);
    }
}
