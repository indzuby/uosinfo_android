package com.uos.uosinfo.utils;

import android.content.Context;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.uos.uosinfo.domain.College;
import com.uos.uosinfo.domain.Library;
import com.uos.uosinfo.domain.PathFinder;
import com.uos.uosinfo.domain.ResultPathFinder;
import com.uos.uosinfo.domain.Word;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 2016-01-12.
 */
public class DataBaseUtils {
    Context mContext;

    public DataBaseUtils(Context context) {
        this.mContext = context;
    }

    public List<Library> getMoreLibrary(Date lastDate) {
        ParseQuery<Library> query = ParseQuery.getQuery(Library.class);
        if (lastDate == null) {
            query = query.orderByDescending("createdAt");
        } else {
            query = query.whereLessThan("updatedAt", lastDate).orderByDescending("createdAt");
        }
        try {
            return query.find();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Library> selectLibraries() {
        ParseQuery<Library> query = ParseQuery.getQuery(Library.class);
        query = query.orderByDescending("createdAt");
        query.fromPin();
        try {
            return query.find();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<PathFinder> getPathFinderByParse(Date date) {
        ParseQuery<PathFinder> query = ParseQuery.getQuery(PathFinder.class);
        try {
            return query.fromPin().whereLessThanOrEqualTo("startDatetime", date).whereGreaterThanOrEqualTo("endDatetime", date).orderByAscending("displayOrder").find();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public PathFinder getPathFinderByObjectId(String objectId) {
        ParseQuery<PathFinder> query = ParseQuery.getQuery(PathFinder.class);
        query.fromPin();
        query.include("Celebrity");
        try {
            return query.get(objectId);
        } catch (Exception e) {
            return null;
        }
    }
    public ResultPathFinder getResultPathFinderByObjectId(String objectId) {
        ParseQuery<ResultPathFinder> query = ParseQuery.getQuery(ResultPathFinder.class);
        query.fromPin();
        query.include("Celebrity");
        try {
            return query.get(objectId);
        } catch (Exception e) {
            return null;
        }
    }
    public List<ResultPathFinder> getResultPathFinderByCollege(String objectId) {
        ParseQuery<ResultPathFinder> query = ParseQuery.getQuery(ResultPathFinder.class);
        ParseQuery<College> innerQuery = ParseQuery.getQuery(College.class);
        innerQuery.whereEqualTo("objectId",objectId);
        query.fromPin().whereMatchesQuery("college",innerQuery);
        query.include("Celebrity");
        query.include("College");
        try {
            return query.find();
        } catch (Exception e) {
            return null;
        }
    }
    public List<Word> getWords(){
        ParseQuery<Word> query = ParseQuery.getQuery(Word.class);
        try {
            return query.fromPin().find();
        } catch (Exception e) {
            return new ArrayList<>();
        }

    }

    public void getMoreDatabase(Date date, ParseQuery query) {
        query.whereLessThan("updatedAt", date).findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                try {
                    ParseObject.pinAll(objects);
                    int count = SessionUtils.getInt(mContext, CodeDefinition.DATABASE_SYNC,0);
                    SessionUtils.putInt(mContext,CodeDefinition.DATABASE_SYNC,count+1);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }
    public College getCollegeByObjectId(String objectId){
        ParseQuery<College> query = ParseQuery.getQuery(College.class);
        try {
            return query.fromPin().get(objectId);
        } catch (Exception e) {
            return null;
        }

    }

    public List<College> getColleges(){
        ParseQuery<College> query = ParseQuery.getQuery(College.class);
        query.orderByAscending("displayOrder");
        try {
            return query.find();
        } catch (Exception e) {
            return null;
        }

    }
}
