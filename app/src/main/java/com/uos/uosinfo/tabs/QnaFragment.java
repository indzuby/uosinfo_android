package com.uos.uosinfo.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uos.uosinfo.R;
import com.uos.uosinfo.main.UosFragment;

/**
 * Created by user on 2015-12-30.
 */
public class QnaFragment extends UosFragment {

    View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_qna,container,false);
        return mView;
    }
    public void init(){

    }
}
