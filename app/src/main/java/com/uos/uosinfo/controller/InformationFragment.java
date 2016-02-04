package com.uos.uosinfo.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.uos.uosinfo.R;
import com.uos.uosinfo.adapter.CollegeAdapter;
import com.uos.uosinfo.domain.College;
import com.uos.uosinfo.main.UosFragment;
import com.uos.uosinfo.service.InformationService;

import java.util.List;

/**
 * Created by user on 2015-12-30.
 */
public class InformationFragment extends UosFragment {
    ListView mListView;
    CollegeAdapter mCollegeAdapter;
    List<College> mColleges;
    InformationService mInformationService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_information,container,false);
        return mView;
    }
    public void init(){
        mInformationService = new InformationService(getContext());
        mListView = (ListView) mView.findViewById(R.id.college_list_view);
        mColleges = mInformationService.getColleges();
        mCollegeAdapter = new CollegeAdapter(getContext(),mColleges);
        mCollegeAdapter.setLanguage(true);
        mListView.setAdapter(mCollegeAdapter);

    }
}
