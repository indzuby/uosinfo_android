package com.uos.uosinfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uos.uosinfo.R;
import com.uos.uosinfo.domain.Admission;
import com.uos.uosinfo.domain.Department;
import com.uos.uosinfo.domain.DepartmentImage;
import com.uos.uosinfo.domain.Recruitment;
import com.uos.uosinfo.domain.RecruitmentType;
import com.uos.uosinfo.utils.DataBaseUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 주현 on 2016-02-08.
 */
public class DepartmentAdapter extends BaseAdapter{
    Context mContext;
    List<Department> mDepartments;
    Map<String, DepartmentImage> mDepMap;
    Map<String, Admission> mAdMap;
    Map<String, List<Recruitment>> mReMap;
    DataBaseUtils mDataBaseUtils;
    public DepartmentAdapter(Context mContext, List<Department> mDepartments) {
        this.mContext = mContext;
        this.mDepartments = mDepartments;
        mDataBaseUtils = new DataBaseUtils(mContext);
        mDepMap = new HashMap<>();
        mAdMap = new HashMap<>();
        mReMap = new HashMap<>();
    }

    @Override
    public int getCount() {
        return mDepartments.size();
    }

    @Override
    public Department getItem(int position) {
        return mDepartments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.element_department_item, null);
            convertView = v;
        }
        final Department department = getItem(position);
        Admission admission;
        if(mAdMap.containsKey(department.getObjectId()))
            admission = mAdMap.get(department.getObjectId());
        else {
            admission = mDataBaseUtils.getAdmissionByDepartment(department.getObjectId());
            mAdMap.put(department.getObjectId(),admission);
        }
        List<Recruitment> recruitments;
        if(mReMap.containsKey(department.getObjectId()))
            recruitments = mReMap.get(department.getObjectId());
        else {
            recruitments = mDataBaseUtils.getRecruitmentByAdmission(admission.getObjectId());
            mReMap.put(department.getObjectId(),recruitments);
        }
        DepartmentImage departmentImage;
        if(mDepMap.containsKey(department.getObjectId()))
            departmentImage = mDepMap.get(department.getObjectId());
        else {
            departmentImage = mDataBaseUtils.getDepartmentImageByDepartment(department.getObjectId());
            mDepMap.put(department.getObjectId(),departmentImage);
        }
        TextView mTitle = (TextView) convertView.findViewById(R.id.department_title);
        TextView mContents = (TextView) convertView.findViewById(R.id.department_contents);
        ImageView mImage = (ImageView) convertView.findViewById(R.id.department_image);
        ImageButton mInformation = (ImageButton) convertView.findViewById(R.id.information);
        LinearLayout modelContainer = (LinearLayout) convertView.findViewById(R.id.model_container);
        modelContainer.removeAllViews();
        getModelView(recruitments,modelContainer);
        mTitle.setText(department.getName());
        mContents.setText(department.getIntroduction());
        Glide.with(mContext).load(departmentImage.getImage()).into(mImage);
        mInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(department.getUrl());
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(launchBrowser);
            }
        });


        return convertView;
    }
    public void getModelView(List<Recruitment> recruitments, LinearLayout layout){
        List<View> views = new ArrayList<>();
        for(Recruitment recruitment : recruitments) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.element_enter_model, null);
            TextView model = (TextView) v.findViewById(R.id.model);
            model.setSelected(false);
            if (recruitment.getNumber() > 0) model.setSelected(true);

            RecruitmentType type = mDataBaseUtils.getRecruitmentTypeByObjectId(recruitment.getType().getObjectId());
            model.setText(type.getShortTitle());
            v.setTag(new Integer(type.getDisplayOrder()));
            views.add(v);
        }
        Collections.sort(views, new Comparator<View>() {
            @Override
            public int compare(View lhs, View rhs) {
                Integer l = (Integer) lhs.getTag();
                Integer r = (Integer) rhs.getTag();
                return l.compareTo(r);
            }
        });
        for(View v : views)
            layout.addView(v);
    }
}
