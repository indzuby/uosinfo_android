package com.uos.uosinfo.domain;

import com.parse.ParseClassName;

/**
 * Created by user on 2016-01-07.
 */
@ParseClassName("Library")
public class Library extends Common{
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
