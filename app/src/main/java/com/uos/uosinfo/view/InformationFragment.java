package com.uos.uosinfo.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.uos.uosinfo.R;
import com.uos.uosinfo.adapter.CollegeAdapter;
import com.uos.uosinfo.domain.College;
import com.uos.uosinfo.view.main.FloatingPopup;
import com.uos.uosinfo.view.main.UosFragment;
import com.uos.uosinfo.controller.InformationController;

import java.util.List;

/**
 * Created by user on 2015-12-30.
 */
public class InformationFragment extends UosFragment implements View.OnClickListener{
    ListView mListView;
    ImageButton mFloatingPlus;
    private FloatingPopup mFloatingPopup;
    CollegeAdapter mCollegeAdapter;
    List<College> mColleges;
    InformationController mInformationController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_information,container,false);
        return mView;
    }
    public void init(){
        mInformationController = new InformationController(getContext());
        mListView = (ListView) mView.findViewById(R.id.college_list_view);
        mColleges = mInformationController.getColleges();
        mCollegeAdapter = new CollegeAdapter(getContext(),mColleges);
        mCollegeAdapter.setLanguage(true);
        mListView.setAdapter(mCollegeAdapter);
        mFloatingPlus = (ImageButton) mView.findViewById(R.id.plus_button);
        mFloatingPlus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.plus_button:
                mFloatingPopup = new FloatingPopup(getContext());
                mFloatingPopup.show();
                break;
        }
    }
}
