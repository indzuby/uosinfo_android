package com.uos.uosinfo.view.information;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.uos.uosinfo.R;
import com.uos.uosinfo.domain.Admission;
import com.uos.uosinfo.domain.Department;
import com.uos.uosinfo.domain.Recruitment;
import com.uos.uosinfo.domain.RecruitmentType;
import com.uos.uosinfo.utils.DataBaseUtils;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 주현 on 2016-02-18.
 */
public class DepartmentPopup extends Dialog implements View.OnClickListener{
    Department department;
    Admission admission;
    List<Recruitment> recruitments;
    DataBaseUtils mDataBaseUtils;
    public DepartmentPopup(Context context,Department department,Admission admission,List<Recruitment> recruitments) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.department = department;
        this.admission = admission;
        this.recruitments = recruitments;
        mDataBaseUtils = new DataBaseUtils(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_department);
        setLayout();
        findViewById(R.id.layout).setOnClickListener(this);
    }
    public void setLayout(){
        TextView titleView = (TextView) findViewById(R.id.title);
        TextView contentsView = (TextView) findViewById(R.id.contents);
        LinearLayout admissionContainer = (LinearLayout) findViewById(R.id.admission_container);
        TextView admissionTitleView = (TextView) findViewById(R.id.admission_title);
        TextView admissionContentsView = (TextView) findViewById(R.id.admission_contents);
        TextView reqruitmentTitleView = (TextView) findViewById(R.id.recruitment_title);
        LinearLayout recruitmentContianer = (LinearLayout) findViewById(R.id.recruitment_container);
        Button reqruitmentbutton = (Button) findViewById(R.id.reqruitment_button);
        View phoneButton = findViewById(R.id.phone_layout);
        TextView phoneView = (TextView)findViewById(R.id.phone_textview);
        View webButton = findViewById(R.id.web_layout);
        TextView webView =  (TextView) findViewById(R.id.web_textview);


        titleView.setText(department.getName());
        contentsView.setText(department.getIntroduction());
        String contents = admission.getContent();
        if(contents == null || contents.isEmpty())
            admissionContainer.setVisibility(View.GONE);
        else {
            admissionContainer.setVisibility(View.VISIBLE);
            admissionTitleView.setText((new DateTime().getYear()+1)+"학년도 "+department.getName()+" 인재상");
            admissionContentsView.setText(contents);
        }
        reqruitmentTitleView.setText((new DateTime().getYear()+1) + "학년도 압학사정관제 모집정원");
        recruitmentContianer.removeAllViews();
        getReqruitment(recruitments, recruitmentContianer);

        phoneView.setText(department.getPhone());
        webView.setText(department.getUrl());


        phoneButton.setOnClickListener(this);
        webButton.setOnClickListener(this);
        reqruitmentbutton.setOnClickListener(this);
     }
    public void getReqruitment(List<Recruitment> recruitments, LinearLayout layout){
        List<View> views = new ArrayList<>();
        for(Recruitment recruitment : recruitments) {
            View v = getLayoutInflater().inflate(R.layout.element_reqruitment,null);
            TextView count = (TextView) v.findViewById(R.id.reqruitment_count);
            TextView name = (TextView) v.findViewById(R.id.reqruitment_name);

            RecruitmentType type = mDataBaseUtils.getRecruitmentTypeByObjectId(recruitment.getType().getObjectId());
            name.setText(type.getTitle());
            if(recruitment.getNumber()>0)
                count.setSelected(true);
            else count.setSelected(false);
            count.setText(String.format("%02d",recruitment.getNumber()));
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
        for(View v : views) {
            LinearLayout.LayoutParams params = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            v.setLayoutParams(params);
            layout.addView(v);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getId() == R.id.layout)
            dismiss();
        else if (v.getId() == R.id.phone_layout) {
            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + department.getPhone()));
            getContext().startActivity(intent);

        }else if(v.getId() == R.id.web_layout || v.getId() == R.id.reqruitment_button) {
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(admission.getUrl()));
            getContext().startActivity(launchBrowser);
        }
    }
}
