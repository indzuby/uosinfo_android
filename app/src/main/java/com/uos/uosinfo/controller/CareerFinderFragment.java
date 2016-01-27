package com.uos.uosinfo.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uos.uosinfo.R;
import com.uos.uosinfo.common.ConfirmPopup;
import com.uos.uosinfo.main.UosFragment;
import com.uos.uosinfo.utils.CodeDefinition;
import com.uos.uosinfo.utils.SessionUtils;

/**
 * Created by user on 2015-12-30.
 */
public class CareerFinderFragment extends UosFragment{
    boolean isFirst;
    RelativeLayout mWordContainer;
    LinearLayout mPopupContainer;
    int mStep;
    boolean isFirstCancel = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_career_finder,container,false);
        initView();
        return mView;
    }
    public void initView(){
        mWordContainer = (RelativeLayout)mView.findViewById(R.id.word_container);
        mPopupContainer = (LinearLayout) mView.findViewById(R.id.popup_container);
    }
    public void init(){
        isFirst = SessionUtils.getBoolean(getContext(), CodeDefinition.CAREER_RESULT, false);
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
        if(isFirstCancel) {
            mWordContainer.setVisibility(View.GONE);
            mPopupContainer.setVisibility(View.VISIBLE);
            TextView contentsView = (TextView) mPopupContainer.findViewById(R.id.contents);
            contentsView.setText(getContext().getString(R.string.career_find_popup_1));
            mPopupContainer.findViewById(R.id.step1).setSelected(true);
            mPopupContainer.findViewById(R.id.cancel).setOnClickListener(informationListener);
            mPopupContainer.findViewById(R.id.next).setOnClickListener(informationListener);
            mStep = 0;
            setNextStep();
        }
    }
    private void startCareerFindTest(){
        mPopupContainer.setVisibility(View.GONE);
        mWordContainer.setVisibility(View.VISIBLE);
    }
    View.OnClickListener informationListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.next) {
                if(setNextStep()) {
                    startCareerFindTest();
                    isFirstCancel = false;
                }
            }
            if(v.getId() == R.id.cancel) {
                mPopupContainer.setVisibility(View.GONE);
                isFirstCancel = true;
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

    public boolean setNextStep() {
        TextView contentsView = (TextView) mPopupContainer.findViewById(R.id.contents);
        mStep++;
        if(mStep>3) {
            return true;
        }
        switch (mStep) {
            case 1:
                contentsView.setText(getContext().getString(R.string.career_find_popup_1));
                break;
            case 2:
                contentsView.setText(getContext().getString(R.string.career_find_popup_2));
                break;
            case 3:
                contentsView.setText(getContext().getString(R.string.career_find_popup_3));
                break;
        }
        mPopupContainer.findViewById(R.id.step1).setSelected(false);
        mPopupContainer.findViewById(R.id.step2).setSelected(false);
        mPopupContainer.findViewById(R.id.step3).setSelected(false);
        if (mStep >= 1)
            mPopupContainer.findViewById(R.id.step1).setSelected(true);
        if (mStep >= 2)
            mPopupContainer.findViewById(R.id.step2).setSelected(true);
        if(mStep>=3) {
            mPopupContainer.findViewById(R.id.step3).setSelected(true);
            TextView next = (TextView)  mPopupContainer.findViewById(R.id.next);
            next.setText("검사 시작");
        }
        return false;
    }
}
