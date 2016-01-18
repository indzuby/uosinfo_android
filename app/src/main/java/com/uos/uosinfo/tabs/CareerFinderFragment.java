package com.uos.uosinfo.tabs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uos.uosinfo.R;
import com.uos.uosinfo.main.UosFragment;
import com.uos.uosinfo.tabs.careerfinder.InformationPopup;
import com.uos.uosinfo.utils.SessionUtils;

/**
 * Created by user on 2015-12-30.
 */
public class CareerFinderFragment extends UosFragment{
    View mView;
    boolean isFirst;
    InformationPopup mInformationPopup;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_career_finder,container,false);
        return mView;
    }

    public void init(){
        isFirst = SessionUtils.getBoolean(getContext(), "career_result", false);
        if(isFirst) {
            firstPopupInit();
        }else {
            reStartPopupInit();
        }
    }
    private void reStartPopupInit(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());

        dialog.setMessage("기존 검사 결과가 있습니다.\n검사를 다시하시겠습니까? ");
        dialog.setPositiveButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.setNegativeButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startCareerFindTest();
            }
        });
        dialog.show();
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
}
