package com.uos.uosinfo.database;

import android.content.Context;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.uos.uosinfo.adapter.LibraryAdapter;
import com.uos.uosinfo.domain.Library;
import com.uos.uosinfo.domain.PathFinder;
import com.uos.uosinfo.utils.ParseUtils;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Months;

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
            e.printStackTrace();
        }
        return false;
    }

    public List<PathFinder> selectPathFinderThisMonth() {
        try {
            DateTime dt = new DateTime();
            return mHelper.selectPathFinderByMonth(dt.getMonthOfYear());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    int[] beforeMonth = {0,12,1,2,3,4,5,6,7,8,9,10,11};
    public List<PathFinder> selectPathFinderLastMonth() {
        DateTime dt = new DateTime();
        return selectPathFinderByMonth(beforeMonth[dt.getMonthOfYear()]);
    }
    private List<PathFinder> selectPathFinderByMonth(int month) {
        try {
            return mHelper.selectPathFinderByMonth(month);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
