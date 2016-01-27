package com.uos.uosinfo.utils;

import android.content.Context;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.uos.uosinfo.domain.Library;
import com.uos.uosinfo.domain.PathFinder;

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

    public List<PathFinder> getPathFinderByParse(Date date, boolean inPin) {
        ParseQuery<PathFinder> query = ParseQuery.getQuery(PathFinder.class);
        if (inPin)
            query.fromPin();
        try {
            return query.whereLessThanOrEqualTo("startDatetime", date).whereGreaterThanOrEqualTo("endDatetime", date).orderByAscending("displayOrder").find();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public PathFinder getPathFinderByObjectId(String objectId) {
        ParseObject.registerSubclass(PathFinder.class);
        ParseQuery<PathFinder> query = ParseQuery.getQuery(PathFinder.class);
        query.fromPin();
        query.include("Celebrity");
        try {
            return query.get(objectId);
        } catch (Exception e) {
            return null;
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
}
