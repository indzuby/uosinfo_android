package com.uos.uosinfo.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by user on 2016-01-20.
 */
@DatabaseTable(tableName = "notice")
public class Notice {
    @DatabaseField(columnName = "object_id",id = true)
    String objectId;
    @DatabaseField
    String title;
    @DatabaseField
    String content;
    @DatabaseField
    String url;
    @DatabaseField(columnName = "is_new")
    Boolean isNew;
    @DatabaseField(columnName = "create_datetime")
    Date createDatetime;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }
}
