package com.uos.uosinfo.domain;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 2016-01-07.
 */

public class Library {

    String title;
    Date createdAt;
    String url;
    String contents;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public static class PasreUtil{
        public static List<Library> parseLibrary(List<ParseObject> libraries) {
            List<Library> lib = new ArrayList<>();
            for(ParseObject object : libraries) {
                Library library = new Library();
                library.setTitle(object.getString("title"));
                library.setCreatedAt(object.getCreatedAt());
                library.setUrl(object.getString("url"));
                library.setContents(object.getString("content"));
                lib.add(library);
            }
            return lib;
        }
    }
}
