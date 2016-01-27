package com.uos.uosinfo.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.uos.uosinfo.R;
import com.uos.uosinfo.adapter.LibraryAdapter;
import com.uos.uosinfo.domain.Library;
import com.uos.uosinfo.main.UosFragment;
import com.uos.uosinfo.utils.DataBaseUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 2015-12-30.
 */
public class LibraryFragment extends UosFragment {
    final String TAG = "Library Fragment";
    ListView mListView;
    LibraryAdapter adapter;
    List<Library> list;
    DataBaseUtils mDataBaseUtils;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_library,container,false);
        return mView;
    }
    public Date getLastDate(){
        if(list==null || list.isEmpty())
            return null;
        return list.get(0).getCreatedAt();
    }
    public void init(){
        mDataBaseUtils = new DataBaseUtils(getContext());
        mListView = (ListView) mView.findViewById(R.id.library_list_view);

        list = mDataBaseUtils.selectLibraries();
        adapter = new LibraryAdapter(getActivity(), list);
        mListView.setAdapter(adapter);
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
    }

    public int getIndexItem(String objectId) {
        for (int i=0;i<list.size();i++ ) {
            Library library = list.get(i);
            if (library.getObjectId().equals(objectId))
                return i;
        }
        return -1;
    }
}
