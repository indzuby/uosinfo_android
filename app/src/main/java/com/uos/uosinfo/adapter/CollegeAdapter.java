package com.uos.uosinfo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uos.uosinfo.R;
import com.uos.uosinfo.domain.College;

import java.util.List;

/**
 * Created by 주현 on 2016-02-04.
 */
public class CollegeAdapter extends BaseAdapter{
    Context mContext;
    List<College> mColleges;
    boolean language;
    public CollegeAdapter(Context mContext, List<College> mColleges) {
        this.mContext = mContext;
        this.mColleges = mColleges;
        language = false;
    }

    @Override
    public int getCount() {
        return mColleges.size();
    }

    @Override
    public College getItem(int position) {
        return mColleges.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setLanguage(boolean language) {
        this.language = language;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.element_career_body_subject, null);
            convertView = v;
        }
        final College college = getItem(position);
        TextView title = (TextView) convertView.findViewById(R.id.department_title);
        if(!language)
            title.setText(college.getNameEn());
        else
            title.setText(college.getName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("GOTO DEPARTMENT","OK");
                // 학과 엑티비티로 이동
            }
        });
        return convertView;
    }
}
