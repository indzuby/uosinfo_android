package com.uos.uosinfo.domain;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 주현 on 2016-01-10.
 */
public class PathFinder {
    String name;
    String image;
    String book;
    Date startDatetime;
    Date endDatetime;
    Ko ko;
    En en;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public Ko getKo() {
        return ko;
    }

    public void setKo(Ko ko) {
        this.ko = ko;
    }

    public En getEn() {
        return en;
    }

    public void setEn(En en) {
        this.en = en;
    }

    public static class Ko {
        String title;
        String field;
        String wiki;
        String college;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getWiki() {
            return wiki;
        }

        public void setWiki(String wiki) {
            this.wiki = wiki;
        }

        public String getCollege() {
            return college;
        }

        public void setCollege(String college) {
            this.college = college;
        }
    }
    public static class En {
        String title;
        String field;
        String wiki;
        String college;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getWiki() {
            return wiki;
        }

        public void setWiki(String wiki) {
            this.wiki = wiki;
        }

        public String getCollege() {
            return college;
        }

        public void setCollege(String college) {
            this.college = college;
        }
    }
    public static class PasreUtil{
        public static List<PathFinder> parseLibrary(List<ParseObject> pass) {
            List<PathFinder> pathFinders = new ArrayList<>();
            for(ParseObject object : pass) {
                PathFinder pathFinder = new PathFinder();
                Ko ko = new Ko();
                En en = new En();
                pathFinder.setName(object.getString("name"));
                pathFinder.setBook(object.getString("book"));
                pathFinder.setImage(object.getString("image"));
                pathFinder.setStartDatetime(object.getDate("startDatetime"));
                pathFinder.setEndDatetime(object.getDate("endDatetime"));
                ko.setCollege(object.getString("collegeKo"));
                ko.setField(object.getString("fieldKo"));
                ko.setTitle(object.getString("titleKo"));
                ko.setWiki(object.getString("wikiKo"));
                en.setCollege(object.getString("collegeEn"));
                en.setField(object.getString("fieldEn"));
                en.setTitle(object.getString("titleEn"));
                en.setWiki(object.getString("wikiEn"));
                pathFinder.setKo(ko);
                pathFinder.setEn(en);

                pathFinders.add(pathFinder);
            }
            return pathFinders;
        }
    }
}
