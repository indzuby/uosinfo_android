package com.uos.uosinfo.domain;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by user on 2016-01-21.
 */
@ParseClassName("Recruitment")
public class Recruitment extends Common{

    public int getNumber(){
        return getInt("number");
    }

    RecruitmentType type;
    public RecruitmentType getType(){
        if(type !=null)
            return type;
        try {
            ParseQuery<RecruitmentType> query = ParseQuery.getQuery(RecruitmentType.class);
            query.fromPin();
            return (type = query.get(getParseObject("type").getObjectId()));
        }catch (ParseException e){
            e.printStackTrace();
            return null;
        }
    }

    Admission admission;
    public Admission getAdmission(){
        if(admission !=null)
            return admission;
        try {
            ParseQuery<Admission> query = ParseQuery.getQuery(Admission.class);
            query.fromPin();
            return (admission = query.get(getParseObject("admission").getObjectId()));
        }catch (ParseException e){
            e.printStackTrace();
            return null;
        }
    }
}
