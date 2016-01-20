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
    @DatabaseField(columnName = "start_datetime")
    Date startDatetime;
    @DatabaseField(columnName = "end_datetime")
    Date endDatetime;
    @DatabaseField(columnName = "great_man",foreign = true)
    GreatMan greatMan;

    @DatabaseField(columnName = "display_order",defaultValue = "0")
    Integer displayOrder;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
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

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public GreatMan getGreatMan() {
        return greatMan;
    }

    public void setGreatMan(GreatMan greatMan) {
        this.greatMan = greatMan;
    }
}
