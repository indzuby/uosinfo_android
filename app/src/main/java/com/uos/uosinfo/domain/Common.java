package com.uos.uosinfo.domain;

import com.parse.ParseObject;

/**
 * Created by 주현 on 2016-02-16.
 */
public class Common extends ParseObject {
    public boolean getValid() {
        return getBoolean("valid");
    }

    public void setValid(boolean valid) {
        put("valid",valid);
    }
}
