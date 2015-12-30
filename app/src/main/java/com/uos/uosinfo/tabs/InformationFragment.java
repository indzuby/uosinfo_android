package com.uos.uosinfo.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uos.uosinfo.R;

/**
 * Created by user on 2015-12-30.
 */
public class InformationFragment extends Fragment {
    View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_information,container,false);
        return mView;
    }

}
