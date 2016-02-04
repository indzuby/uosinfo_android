package com.uos.uosinfo.service;

import android.content.Context;

import com.uos.uosinfo.domain.PathFinder;
import com.uos.uosinfo.utils.DataBaseUtils;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

/**
 * Created by 주현 on 2016-02-04.
 */
public class PathFinderService {
    Context mContext;
    DataBaseUtils mDataBaseUtils;
    public PathFinderService(Context mContext){
        this.mContext = mContext;
        mDataBaseUtils = new DataBaseUtils(mContext);
    }
    public List<PathFinder> getPathFinderThisMonthByDataBase(){
        return mDataBaseUtils.getPathFinderByParse(new Date());

    }
    public List<PathFinder> getPathFinderLastMonthByDataBase(){
        return  mDataBaseUtils.getPathFinderByParse(new DateTime().minusMonths(1).toDate());
    }
}
