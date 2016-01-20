package com.uos.uosinfo.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by 주현 on 2016-01-10.
 */
@DatabaseTable(tableName = "great_man")
public class GreatMan {
    @DatabaseField(columnName ="object_id",id = true)
    String objectId;
    @DatabaseField
    String name;
    @DatabaseField
    String image;
    @DatabaseField
    String book;
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
    @DatabaseField(columnName = "college",foreign = true)
    College college;
    @DatabaseField(columnName = "create_datetime")
    Date createDatetime;

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

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }
}
