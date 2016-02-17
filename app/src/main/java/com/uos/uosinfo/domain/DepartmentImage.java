package com.uos.uosinfo.domain;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by 주현 on 2016-02-03.
 */

@ParseClassName("DepartmentImage")
public class DepartmentImage extends Common{

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
    public String getImage(){
        return getString("android");
    }
}
