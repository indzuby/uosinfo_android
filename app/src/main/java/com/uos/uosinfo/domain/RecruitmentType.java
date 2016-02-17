package com.uos.uosinfo.domain;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by user on 2016-01-21.
 */
@ParseClassName("RecruitmentType")
public class RecruitmentType extends Common{

    public String getTitle(){
        return getString("title");
    }
    public void setTitle(String value){
        put("title",value);
    }
    public String getShortTitle(){
        return getString("shortTitle");
    }
    public void setShortTitle(String value){
        put("shortTitle",value);
    }
    public int getDisplayOrder() {return getInt("displayOrder");}
}
