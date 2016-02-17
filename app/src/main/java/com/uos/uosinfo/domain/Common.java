package com.uos.uosinfo.domain;

import com.parse.ParseObject;

/**
 * Created by 주현 on 2016-02-16.
 */
public class Common extends ParseObject {
    public String getValid() {
        return getString("valid");
    }

    public void setValid(String valid) {
        put("valid",valid);
    }
}
