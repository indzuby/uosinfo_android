package com.uos.uosinfo.domain;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by user on 2016-01-21.
 */
@ParseClassName("Admission")
public class Admission extends Common {

    public String getUrl() {
        return getString("url");
    }

    public void setUrl(String value) {
        put("url", value);
    }

    public String getContent() {
        return getString("rightPeople");
    }

    public void setContent(String value) {
        put("content", value);
    }

    Department department;
    public Department getDepartment(){
        if(department !=null)
            return department;
        try {
            ParseQuery<Department> query = ParseQuery.getQuery(Department.class);
            query.fromPin();
            return (department = query.get(getParseObject("department").getObjectId()));
        }catch (ParseException e){
            e.printStackTrace();
            return null;
        }
    }

    public void setDepartment(Department value) {
        put("department", value);
    }
}
