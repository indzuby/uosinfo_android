package com.uos.uosinfo.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by user on 2016-01-07.
 */
@ParseClassName("Library")
public class Library extends ParseObject{
    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        put("title",title);
    }

    public String getUrl() {
        return getString("url");
    }

    public void setUrl(String url) {
        put("url",url);
    }

    public String getContent() {
        return getString("content");
    }

    public void setContent(String content) {
        put("content",content);
    }

    public boolean isNew() {
        return getBoolean("isNew");
    }

    public void setIsNew(boolean isNew) {
        put("isNew",isNew);
    }
}
