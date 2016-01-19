package com.uos.uosinfo.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uos.uosinfo.R;
import com.uos.uosinfo.common.ConfirmPopup;
import com.uos.uosinfo.controller.careerfinder.InformationPopup;
import com.uos.uosinfo.main.UosFragment;
import com.uos.uosinfo.utils.SessionUtils;

/**
 * Created by user on 2015-12-30.
 */
public class CareerFinderFragment extends UosFragment{
    boolean isFirst;
    InformationPopup mInformationPopup;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_career_finder,container,false);
        return mView;
    }

    public void init(){
        isFirst = SessionUtils.getBoolean(getContext(), "career_result", false);
        if(!isFirst) {
            firstPopupInit();
        }else {
            reStartPopupInit();
        }
    }
    ConfirmPopup restartPopup;
    private void reStartPopupInit(){
        restartPopup = new ConfirmPopup(getContext(), getString(R.string.career_restart_popup),restartListener);
    }
    private void firstPopupInit(){
        mInformationPopup = new InformationPopup(getContext(),informationListener);
        mInformationPopup.show();
    }
    private void startCareerFindTest(){

    }
    View.OnClickListener informationListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.next) {
                if(mInformationPopup.setNextStep()) {
                    mInformationPopup.dismiss();
                    startCareerFindTest();
                }
            }
        }
    };

    View.OnClickListener restartListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.yes:
                    startCareerFindTest();
                    break;
                case R.id.no:
                    // 기존 데이터 가져오기
                    break;
            }
            restartPopup.dismiss();
        }
    };
}
