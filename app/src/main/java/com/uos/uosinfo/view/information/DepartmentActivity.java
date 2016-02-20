package com.uos.uosinfo.view.information;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.uos.uosinfo.R;
import com.uos.uosinfo.adapter.DepartmentAdapter;
import com.uos.uosinfo.controller.InformationController;
import com.uos.uosinfo.domain.College;
import com.uos.uosinfo.domain.Department;
import com.uos.uosinfo.view.main.BaseActivity;

import java.util.List;

/**
 * Created by 주현 on 2016-02-08.
 */
public class DepartmentActivity extends BaseActivity implements View.OnClickListener{
    String mObjectId;
    List<Department> mDepartments;
    College mCollege;
    InformationController mInformationController;
    ListView mListView ;
    DepartmentAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departmenet);
        mObjectId = getIntent().getStringExtra("objectId");

        init();
    }
    public void init(){
        mInformationController = new InformationController(this);
        mDepartments = mInformationController.getDepartmentsByCollege(mObjectId);
        mCollege = mInformationController.getCollegeByObjectId(mObjectId);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(mCollege.getName());
        findViewById(R.id.back_button).setOnClickListener(this);
        mListView = (ListView) findViewById(R.id.departmenet_listview);
        mAdapter = new DepartmentAdapter(this,mDepartments);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.back_button)
            finish();
    }
}
