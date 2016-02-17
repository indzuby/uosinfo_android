package com.uos.uosinfo.controller;

import android.content.Context;

import com.uos.uosinfo.domain.College;
import com.uos.uosinfo.domain.Department;
import com.uos.uosinfo.utils.DataBaseUtils;

import java.util.List;

/**
 * Created by 주현 on 2016-02-04.
 */
public class InformationController {
    Context mContext;
    DataBaseUtils mDataBaseUtils;

    public InformationController(Context mContext) {
        this.mContext = mContext;
        mDataBaseUtils = new DataBaseUtils(mContext);
    }
    public List<College> getColleges(){
        return mDataBaseUtils.getColleges();
    }
    public College getCollegeByObjectId(String obejctId){
        return mDataBaseUtils.getCollegeByObjectId(obejctId);
    }
    public List<Department> getDepartmentsByCollege(String collegeId) {
        return mDataBaseUtils.getDepartmentByCollege(collegeId);
    }
}
