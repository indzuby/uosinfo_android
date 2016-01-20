package com.uos.uosinfo.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by user on 2016-01-20.
 */
@DatabaseTable(tableName = "result_pathfinder")
public class ResultPathFinder {
    @DatabaseField(columnName = "object_id",id = true)
    String objectId;

    @DatabaseField(columnName = "great_man",foreign = true)
    GreatMan greatMan;
    @DatabaseField(columnName = "create_datetime")
    Date createDatetime;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public GreatMan getGreatMan() {
        return greatMan;
    }

    public void setGreatMan(GreatMan greatMan) {
        this.greatMan = greatMan;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }
}
