package com.uos.uosinfo.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by user on 2016-01-20.
 */
@DatabaseTable(tableName = "word")
public class Word {
    @DatabaseField(columnName ="object_id",id = true)
    String objectId;
    @DatabaseField
    String word;
    @DatabaseField(columnName = "college_1",foreign = true)
    College college1;
    @DatabaseField(columnName = "college_2",foreign = true)
    College college2;
    @DatabaseField(columnName = "create_datetime")
    Date createDatetime;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public College getCollege1() {
        return college1;
    }

    public void setCollege1(College college1) {
        this.college1 = college1;
    }

    public College getCollege2() {
        return college2;
    }

    public void setCollege2(College college2) {
        this.college2 = college2;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

}
