package com.uos.uosinfo.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.uos.uosinfo.R;
import com.uos.uosinfo.adapter.LibraryAdapter;
import com.uos.uosinfo.domain.Library;
import com.uos.uosinfo.view.main.FloatingPopup;
import com.uos.uosinfo.view.main.UosFragment;
import com.uos.uosinfo.utils.DataBaseUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 2015-12-30.
 */
public class LibraryFragment extends UosFragment implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener{
    final String TAG = "Library Fragment";
    ImageButton mFloatingPlus;
    private FloatingPopup mFloatingPopup;
    ListView mListView;
    LibraryAdapter adapter;
    List<Library> list;
    DataBaseUtils mDataBaseUtils;
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_library,container,false);
        return mView;
    }
    public Date getLastDate(){
        if(list==null || list.isEmpty())
            return null;
        return mDataBaseUtils.getLastLibrary();
    }
    public void init(){
        mDataBaseUtils = new DataBaseUtils(getContext());
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swype_layout);
        mListView = (ListView) mView.findViewById(R.id.library_list_view);
        mFloatingPlus = (ImageButton) mView.findViewById(R.id.plus_button);
        mFloatingPlus.setOnClickListener(this);
        list = mDataBaseUtils.getLibraries();
        adapter = new LibraryAdapter(getActivity(), list);
        mListView.setAdapter(adapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        getMoreLibrary();
    }

    @Override
    public void onRefresh() {
        getMoreLibrary();
    }

    public void getMoreLibrary(){
        Date lastDate = getLastDate();
        List<Library> libraries = mDataBaseUtils.getMoreLibrary(lastDate);
        try {
            ParseObject.pinAll(libraries);
        }catch (ParseException e2) {
            e2.printStackTrace();
        }
        for (Library library : libraries) {
            int index = getIndexItem(library.getObjectId());
            if(index == -1)
                list.add(0, library);
            else
                list.set(index,library);
        }
        adapter.notifyDataSetChanged();
        if(mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
    }

    public int getIndexItem(String objectId) {
        for (int i=0;i<list.size();i++ ) {
            Library library = list.get(i);
            if (library.getObjectId().equals(objectId))
                return i;
        }
        return -1;
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
