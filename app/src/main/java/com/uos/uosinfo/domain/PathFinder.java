package com.uos.uosinfo.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by 주현 on 2016-01-10.
 */
@DatabaseTable(tableName = "pathfinder")
public class PathFinder {
    @DatabaseField(columnName ="object_id",id = true)
    String objectId;
    @DatabaseField
    String name;
    @DatabaseField
    String image;
    @DatabaseField
    String book;
    @DatabaseField(columnName = "start_datetime")
    Date startDatetime;
    @DatabaseField(columnName = "end_datetime")
    Date endDatetime;
    @DatabaseField(columnName = "title_ko")
    String titleKo;
    @DatabaseField(columnName = "title_en")
    String titleEn;
    @DatabaseField(columnName = "wiki_ko")
    String wikiKo;
    @DatabaseField(columnName = "wiki_en")
    String wikiEn;
    @DatabaseField(columnName = "field_ko")
    String fieldKo;
    @DatabaseField(columnName = "field_en")
    String fieldEn;
    @DatabaseField(columnName = "college_ko")
    String collegeKo;
    @DatabaseField(columnName = "college_en")
    String collegeEn;

    @DatabaseField(columnName = "display_order",defaultValue = "0")
    Integer displayOrder;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

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

    public String getTitleKo() {
        return titleKo;
    }

    public void setTitleKo(String titleKo) {
        this.titleKo = titleKo;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getWikiKo() {
        return wikiKo;
    }

    public void setWikiKo(String wikiKo) {
        this.wikiKo = wikiKo;
    }

    public String getWikiEn() {
        return wikiEn;
    }

    public void setWikiEn(String wikiEn) {
        this.wikiEn = wikiEn;
    }

    public String getFieldKo() {
        return fieldKo;
    }

    public void setFieldKo(String fieldKo) {
        this.fieldKo = fieldKo;
    }

    public String getFieldEn() {
        return fieldEn;
    }

    public void setFieldEn(String fieldEn) {
        this.fieldEn = fieldEn;
    }

    public String getCollegeKo() {
        return collegeKo;
    }

    public void setCollegeKo(String collegeKo) {
        this.collegeKo = collegeKo;
    }

    public String getCollegeEn() {
        return collegeEn;
    }

    public void setCollegeEn(String collegeEn) {
        this.collegeEn = collegeEn;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

}
