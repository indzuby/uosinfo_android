package com.uos.uosinfo.view.careerfinder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uos.uosinfo.R;
import com.uos.uosinfo.view.common.PathFinderInterface;
import com.uos.uosinfo.view.CareerFinderFragment;
import com.uos.uosinfo.domain.ResultPathFinder;
import com.uos.uosinfo.utils.CodeDefinition;
import com.uos.uosinfo.utils.DataBaseUtils;

/**
 * Created by 주현 on 2016-02-04.
 */
public class CareerResultCelebrityFragment extends Fragment implements PathFinderInterface {
    View mView;
    TextView mTitle;
    TextView mPerson;
    ImageView mImage;
    DataBaseUtils mDataBaseUtils;
    String objectId;
    ResultPathFinder mPath;
    ImageButton mTranslate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.element_career_header_card,container,false);
        mDataBaseUtils = new DataBaseUtils(getContext());
        init();
        return mView;
    }
    private void init(){
        objectId = getArguments().getString("objectId");
        mPath = mDataBaseUtils.getResultPathFinderByObjectId(objectId);
        mTitle = (TextView) mView.findViewById(R.id.path_finder_title);
        mPerson = (TextView) mView.findViewById(R.id.path_finder_person);
        mImage = (ImageView) mView.findViewById(R.id.path_finder_image);
        mTranslate = (ImageButton) mView.findViewById(R.id.translate_button);
        Glide.with(getActivity())
                .load(mPath.getCelebrity().getImage())
                .fitCenter().crossFade().into(mImage);
        setLanguage(false);
        if(objectId.equals(CodeDefinition.CAREER_MANY_RESULT_OBJECTID) || objectId.equals(CodeDefinition.CAREER_NO_RESULT_OBJECTID)) {
            mTranslate.setVisibility(View.GONE);
            ((CareerFinderFragment) getParentFragment()).changeLanguage();
        }
        mTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CareerFinderFragment) getParentFragment()).changeLanguage();
            }
        });
    }

    @Override
    public void setLanguage(boolean language) {
        if(!objectId.equals(CodeDefinition.CAREER_MANY_RESULT_OBJECTID) && !objectId.equals(CodeDefinition.CAREER_NO_RESULT_OBJECTID)) {
            mPerson.setText(mPath.getCelebrity().getName());
            if (language)
                mTitle.setText(mPath.getCelebrity().getTitleKo());
            else
                mTitle.setText(mPath.getCelebrity().getTitleEn());
        }else {
            mTitle.setText("");
            mPerson.setText("");
        }
    }
}
