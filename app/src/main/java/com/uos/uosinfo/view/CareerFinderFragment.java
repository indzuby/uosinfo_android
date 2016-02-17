package com.uos.uosinfo.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uos.uosinfo.R;
import com.uos.uosinfo.adapter.CollegeAdapter;
import com.uos.uosinfo.adapter.CareerWordAdapter;
import com.uos.uosinfo.adapter.PathFinderAdapter;
import com.uos.uosinfo.view.common.AlertPopup;
import com.uos.uosinfo.view.common.ConfirmPopup;
import com.uos.uosinfo.view.careerfinder.CareerResultCelebrityFragment;
import com.uos.uosinfo.domain.CareerResult;
import com.uos.uosinfo.domain.College;
import com.uos.uosinfo.domain.ResultPathFinder;
import com.uos.uosinfo.domain.Word;
import com.uos.uosinfo.view.main.UosFragment;
import com.uos.uosinfo.controller.CareerFinderController;
import com.uos.uosinfo.ui.PagerPoint;
import com.uos.uosinfo.utils.CodeDefinition;
import com.uos.uosinfo.utils.SessionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2015-12-30.
 */
public class CareerFinderFragment extends UosFragment{
    int[] mStepBars ={R.id.step1,R.id.step2,R.id.step3,R.id.step4,R.id.step5,R.id.step6};
    boolean hasResult;
    RelativeLayout mWordContainer;
    LinearLayout mPopupContainer;
    LinearLayout mResultContainer;
    int mStep;
    int mTestStep;
    boolean isFirstCancel = true;
    ListView mListView;
    CareerWordAdapter mWordAdapter;
    TextView mNext;

    CareerFinderController mCareerService;

    List<CareerResult> mResultObjectId;
    ViewPager mViewPager;
    PathFinderAdapter mPathFinderAdapter;
    List<ResultPathFinder> mPath;
    List<Fragment> mFragments;
    LinearLayout mOvalContainer;
    ListView mResultListView;
    private boolean language = false; // false : En , true : Ko;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_career_finder,container,false);
        initView();
        return mView;
    }
    public void initView(){
        mWordContainer = (RelativeLayout)mView.findViewById(R.id.word_container);
        mPopupContainer = (LinearLayout) mView.findViewById(R.id.popup_container);
        mResultContainer = (LinearLayout) mView.findViewById(R.id.result_container);
        mListView = (ListView) mView.findViewById(R.id.word_list);
        mNext = (TextView) mView.findViewById(R.id.next);
        mNext.setOnClickListener(mNextListener);
        mCareerService = new CareerFinderController(getActivity());
    }
    public void init(){
        hasResult = SessionUtils.getBoolean(getContext(), CodeDefinition.CAREER_RESULT, false);
        if(!hasResult) {
            firstPopupInit();
        }else {
            reStartPopupInit();
        }
    }
    ConfirmPopup restartPopup;
    private void reStartPopupInit(){

        restartPopup = new ConfirmPopup(getContext(), getString(R.string.career_restart_popup),restartListener);
        restartPopup.show();
    }
    private void firstPopupInit(){
        if(isFirstCancel) {
            mWordContainer.setVisibility(View.GONE);
            mResultContainer.setVisibility(View.GONE);
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
    CollegeAdapter mResultAdapter;
    View mHeaderView;
    private void initResultView(){
        mPopupContainer.setVisibility(View.GONE);
        mWordContainer.setVisibility(View.GONE);
        mResultContainer.setVisibility(View.VISIBLE);
        SessionUtils.putBoolean(getContext(), CodeDefinition.CAREER_RESULT, true);
        hasResult = true;
        language = false;
        mPath = new ArrayList<>();
        mFragments = new ArrayList<>();
        mResultListView = (ListView) mView.findViewById(R.id.college_list_view);
        if(mHeaderView!=null) mResultListView.removeHeaderView(mHeaderView);
        mHeaderView = LayoutInflater.from(getContext()).inflate(R.layout.element_career_finder_header,null,false);
        mViewPager = (ViewPager) mHeaderView.findViewById(R.id.career_viewpager);
        mOvalContainer = (LinearLayout) mHeaderView.findViewById(R.id.oval_container);
        List<College> colleges = new ArrayList<>();
        String firstChild = mResultObjectId.get(0).getObjectId();
        Double firstValue = mResultObjectId.get(0).getValue();
        mResultListView.addHeaderView(mHeaderView);
        if(firstChild.equals(CodeDefinition.CAREER_MANY_RESULT)) {
            colleges.add(mCareerService.getCollege(CodeDefinition.LIBERAL_OBJECTID));
            mPath.add(mCareerService.getResultPathFinder(CodeDefinition.CAREER_MANY_RESULT_OBJECTID));
        }else if(firstChild.equals(CodeDefinition.CAREER_NO_RESULT)) {
            colleges.add(mCareerService.getCollege(CodeDefinition.LIBERAL_OBJECTID));
            mPath.add(mCareerService.getResultPathFinder(CodeDefinition.CAREER_NO_RESULT_OBJECTID));
        }else {
            for(CareerResult result : mResultObjectId) {
                College college = mCareerService.getCollege(result.getObjectId());
                colleges.add(college);
                if(result.getValue() == firstValue) {
                    mPath.addAll(mCareerService.getResultPathFinderByCollege(result.getObjectId()));
                }
            }
        }
        setFragments();
        setOvalContainer();
        changeOvalState(0);
        mResultAdapter = new CollegeAdapter(getContext(),colleges);
        mPathFinderAdapter = new PathFinderAdapter(getChildFragmentManager(),getActivity(),mFragments);
        mViewPager.setOffscreenPageLimit(mFragments.size() - 1);
        mViewPager.setAdapter(mPathFinderAdapter);
        mResultListView.setAdapter(mResultAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeOvalState(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public void changeLanguage(){
        language = !language;
        mPathFinderAdapter.changeLanguage(language);
        mResultAdapter.setLanguage(language);
        mResultAdapter.notifyDataSetChanged();
        TextView people = (TextView) mHeaderView.findViewById(R.id.path_finder_people);
        TextView recommend = (TextView) mHeaderView.findViewById(R.id.recommend_college);
        if(!language) {
            people.setText(getString(R.string.career_people_en));
            recommend.setText(getString(R.string.career_people_en));
        }else{
            people.setText(getString(R.string.career_people_kr));
            recommend.setText(getString(R.string.career_people_kr));
        }
    }
    private void setFragments(){
        mFragments.clear();
        for(ResultPathFinder pathFinder : mPath) {
            Fragment fragment = new CareerResultCelebrityFragment();
            Bundle bundle = new Bundle();
            bundle.putString("objectId", pathFinder.getObjectId());
            fragment.setArguments(bundle);
            mFragments.add(fragment);
        }
    }
    private void setOvalContainer(){
        mOvalContainer.removeAllViews();
        if(mPath.size()>1) {
            for (int i = 0; i < mPath.size(); i++) {
                mOvalContainer.addView(PagerPoint.getPoint(getActivity()));
            }
        }
    }
    private void changeOvalState(int index){
        if(mPath.size()>1) {
            for (int i = 0; i < mOvalContainer.getChildCount(); i++)
                mOvalContainer.getChildAt(i).setSelected(false);
            mOvalContainer.getChildAt(index).setSelected(true);
        }
    }
    private void startCareerFindTest(){
        mPopupContainer.setVisibility(View.GONE);
        mResultContainer.setVisibility(View.GONE);
        mWordContainer.setVisibility(View.VISIBLE);

        mTestStep = 0;
        mCareerService.shuffleWord();
        for(int i =0 ; i<CodeDefinition.CAREER_STEP_COUNT;i++)
            mView.findViewById(mStepBars[i]).setSelected(false);

        progressNextStep();
    }
    private void progressNextStep(){
        if(mCareerService.isNotSelectedWord()) {
            wordConfirmPopup = new ConfirmPopup(getActivity(), getString(R.string.career_word_confirm), confirmPopupListener);
            wordConfirmPopup.show();
        }else
            gotoNextStep();
    }
    void gotoNextStep(){
        mTestStep++;
        if (mTestStep > CodeDefinition.CAREER_STEP_COUNT) {
            wordAlertPopup = new AlertPopup(getActivity(), getString(R.string.career_result_popup), alertPopupListener);
            wordAlertPopup.setButtonText("결과 보기");
            wordAlertPopup.show();
        }else {
            mCareerService.setBeforeSelectedCountByTotalSelectedCount();
            for (int i = 0; i < mTestStep; i++)
                mView.findViewById(mStepBars[i]).setSelected(true);
            mWordAdapter = new CareerWordAdapter(mCareerService.getNextWords(), getContext(), mNextListener);
            mListView.setAdapter(mWordAdapter);
        }
    }
    ConfirmPopup wordConfirmPopup;
    View.OnClickListener confirmPopupListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.yes)
                gotoNextStep();
            wordConfirmPopup.dismiss();
        }
    };
    AlertPopup wordAlertPopup;
    View.OnClickListener alertPopupListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.yes) {
                wordAlertPopup.dismiss();
                mResultObjectId = mCareerService.gotoResult();
                initResultView();
            }
        }
    };
    View.OnClickListener mNextListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.next) {
                progressNextStep();
            }else if(v.getId() == R.id.word) {
                Word word = (Word) v.getTag();
                int count = mCareerService.getWordCount(word);
                if(!v.isSelected()) {
                    mCareerService.increaseWordCount(count,word);
                    v.setSelected(true);
                }else {
                    mCareerService.decreaseWordCount(count,word);
                    v.setSelected(false);
                }
            }
        }
    };
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
                    mResultObjectId = mCareerService.getResultObjectList();
                    initResultView();
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
