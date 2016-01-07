package com.uos.uosinfo.tabs;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.uos.uosinfo.R;
import com.uos.uosinfo.main.FloatingPopup;
import com.uos.uosinfo.tabs.passfinder.FloatingArrowPopup;

/**
 * Created by user on 2015-12-30.
 */
public class PassFinderFragment extends Fragment implements View.OnClickListener {
    private ImageButton mFloatingPlus;
    private FloatingPopup mfFloatingPopup;
    private ImageButton mFloatingArrow;
    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_pass_finder, container, false);
        init();
        return mView;
    }

    void init() {
        mFloatingPlus = (ImageButton) mView.findViewById(R.id.plus_button);
        mFloatingArrow = (ImageButton) mView.findViewById(R.id.arrow_button);
        mFloatingPlus.setOnClickListener(this);
        mFloatingArrow.setOnClickListener(this);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.schedule_button:
                    break;
                case R.id.notice_button:
                    break;
                case R.id.push_button:
                    break;
                case R.id.phone_button:
                    break;
            }

        }
    };
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.plus_button:
                mfFloatingPopup = new FloatingPopup(getContext(),listener);
                mfFloatingPopup.show();
                break;
            case R.id.arrow_button:
                FloatingArrowPopup mfFloatingArrowPopup = new FloatingArrowPopup(getContext(),listener);
                mfFloatingArrowPopup.show();
        }
    }

}
