package com.uos.uosinfo.domain;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by user on 2016-01-21.
 */
@ParseClassName("Recruitment")
public class Recruitment extends ParseObject{

    public int getNumber(){
        return getInt("number");
    }

}
