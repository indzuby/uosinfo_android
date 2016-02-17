package com.uos.uosinfo.domain;

import com.parse.ParseClassName;

/**
 * Created by user on 2016-01-20.
 */
@ParseClassName("Notice")
public class Notice extends Common{

    public String getTitle() {
        return getString("title");
    }

    public void setTitle(String title) {
        put("title",title);
    }

    public String getContent() {
        return getString("content");
    }

    public void setContent(String content) {
        put("content",content);
    }

    public String getUrl() {
        return getString("url");
    }

    public void setUrl(String url) {
        put("url",url);
    }

    public Boolean isNew() {
        return getBoolean("isNew");
    }

    public void setIsNew(Boolean isNew) {
        put("isNew",isNew);
    }

}
