package com.uos.uosinfo.database;

import android.content.Context;

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
    DataBaseHelper mHelper;

    public DataBaseUtils(Context context) {
        this.mContext = context;
        mHelper = new DataBaseHelper(mContext);
    }

    public void getMoreLibrary(FindCallback<ParseObject> callback) {
        try {
            Date lastDate = mHelper.lastLibraryCreateAt();
            ParseQuery<ParseObject> query;
            if (lastDate == null) {
                query = ParseQuery.getQuery("Library").orderByAscending("createdAt");
            } else {
                query = ParseQuery.getQuery("Library").whereGreaterThan("updatedAt", lastDate).orderByAscending("createdAt");
            }
            query.findInBackground(callback);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertLibraries(List<Library> libraries) {
        try {
            for (Library library : libraries) {
                if (mHelper.isExistLibrary(library.getObjectId()))
                    mHelper.updateLibrary(library);
                else
                    mHelper.insertLibrary(library);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Library> selectLibraries() {
        try {
            return mHelper.selectLibrary();
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public boolean hasPathFinderMonth() {
        try {
            return mHelper.hasPathFinderThisMonth(new Date());
        } catch (SQLException e) {
            return false;
        }
    }

    public List<PathFinder> selectPathFinderThisMonth() {
        return selectPathFinderByDate(new Date());
    }
    public void getPathFinderByParse(Date date,FindCallback<ParseObject> callback) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("PathFinder");
        query.whereLessThanOrEqualTo("startDatetime",date).whereGreaterThanOrEqualTo("endDatetime",date).orderByAscending("displayOrder").findInBackground(callback);
    }
    public List<PathFinder> selectPathFinderLastMonth() {
        return selectPathFinderByDate(new DateTime().minusMonths(1).toDate());
    }
    private List<PathFinder> selectPathFinderByDate(Date date) {
        try {
            return mHelper.selectPathFinderByDate(date);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
    public void insertPathFinders(List<PathFinder> pathFinders) {
        try {
            for (PathFinder finder : pathFinders) {
                mHelper.insertPathFinder(finder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
