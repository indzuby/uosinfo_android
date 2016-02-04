package com.uos.uosinfo.domain;

/**
 * Created by 주현 on 2016-02-04.
 */
public class CareerResult {
    String objectId;
    double value;

    public CareerResult(String objectId, double value) {
        this.objectId = objectId;
        this.value = value;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
