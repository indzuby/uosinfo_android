package com.uos.uosinfo.database;

import android.content.Context;
import android.graphics.Path;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.uos.uosinfo.domain.Library;
import com.uos.uosinfo.domain.PathFinder;

import org.joda.time.DateTime;

import java.sql.SQLException;
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

    public void getMoreLibrary(FindCallback<Library> callback, Date lastDate) {
        ParseQuery<Library> query = ParseQuery.getQuery(Library.class);
        if (lastDate == null) {
            query = query.orderByAscending("createdAt");
        } else {
            query = query.whereGreaterThan("updatedAt", lastDate).orderByAscending("createdAt");
        }
        query.findInBackground(callback);
    }

    public void selectLibraries(FindCallback<Library> callback) {
        ParseQuery<Library> query = ParseQuery.getQuery(Library.class);
        query = query.orderByAscending("createdAt");
        query.fromPin();
        query.findInBackground(callback);
    }

    public void getPathFinderByParse(Date date,FindCallback<PathFinder> callback,boolean inPin) {
        ParseQuery<PathFinder> query = ParseQuery.getQuery(PathFinder.class);
        if(inPin)
            query.fromPin();
        query.whereLessThanOrEqualTo("startDatetime",date).whereGreaterThanOrEqualTo("endDatetime",date).orderByAscending("displayOrder").findInBackground(callback);
    }

}
