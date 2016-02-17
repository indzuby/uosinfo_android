package com.uos.uosinfo.view.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.uos.uosinfo.R;
import com.uos.uosinfo.adapter.NoticeAdapter;
import com.uos.uosinfo.domain.Notice;
import com.uos.uosinfo.utils.DataBaseUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by 주현 on 2016-02-16.
 */
public class NoticeActivity extends BaseAcitvity implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener{
    SwipeRefreshLayout mSwipeRefreshLayout;
    ExpandableListView mListView;
    List<Notice> mNotices;
    NoticeAdapter mNoticeAdapter;
    DataBaseUtils mDataBaseUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        init();
    }
    public void init(){
        mDataBaseUtils = new DataBaseUtils(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        findViewById(R.id.back_button).setOnClickListener(this);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swype_layout);
        mListView = (ExpandableListView) findViewById(R.id.notice_listview);
        mNotices = mDataBaseUtils.getNotices();
        mNoticeAdapter = new NoticeAdapter(this,mNotices);
        mListView.setAdapter(mNoticeAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        getMoreNotice();
    }
    private Date getLastDate(){
        if(mNotices==null || mNotices.isEmpty())
            return null;
        return mDataBaseUtils.getLastNotice();

    }
    public void getMoreNotice(){
        Date lastDate = getLastDate();
        List<Notice> notices = mDataBaseUtils.getMoreNotice(lastDate);
        try {
            ParseObject.pinAll(notices);
        }catch (ParseException e2) {
            e2.printStackTrace();
        }
        for (Notice notice : notices) {
            int index = getIndexItem(notice.getObjectId());
            if(index == -1)
                mNotices.add(0, notice);
            else
                mNotices.set(index,notice);
        }
        mNoticeAdapter.notifyDataSetChanged();
        if(mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);

    }
    @Override
    public void onRefresh() {
        getMoreNotice();
    }

    public int getIndexItem(String objectId) {
        for (int i=0;i<mNotices.size();i++ ) {
            Notice notice = mNotices.get(i);
            if (notice.getObjectId().equals(objectId))
                return i;
        }
        return -1;
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.back_button)
            finish();
    }
}
