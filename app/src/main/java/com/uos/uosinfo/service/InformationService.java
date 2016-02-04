package com.uos.uosinfo.service;

import android.content.Context;

import com.uos.uosinfo.domain.College;
import com.uos.uosinfo.utils.DataBaseUtils;

import java.util.List;

/**
 * Created by 주현 on 2016-02-04.
 */
public class InformationService {
    Context mContext;
    DataBaseUtils mDataBaseUtils;

    public InformationService(Context mContext) {
        this.mContext = mContext;
        mDataBaseUtils = new DataBaseUtils(mContext);
    }
    public List<College> getColleges(){
        return mDataBaseUtils.getColleges();
    }
}
