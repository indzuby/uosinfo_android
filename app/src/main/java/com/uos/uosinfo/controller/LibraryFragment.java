package com.uos.uosinfo.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.uos.uosinfo.R;
import com.uos.uosinfo.adapter.LibraryAdapter;
import com.uos.uosinfo.database.DataBaseUtils;
import com.uos.uosinfo.domain.Library;
import com.uos.uosinfo.main.UosFragment;
import com.uos.uosinfo.utils.ParseUtils;

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

    public void init(){
        mDataBaseUtils = new DataBaseUtils(getContext());
        mListView = (ListView) mView.findViewById(R.id.listView);

        list = mDataBaseUtils.selectLibraries();
        adapter = new LibraryAdapter(getActivity(),list);
        mListView.setAdapter(adapter);

        mDataBaseUtils.getMoreLibrary(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> libraries, ParseException e) {
                if (e == null) {
                    Log.e("TAG", libraries.size() + "");
                    List<Library> newList = ParseUtils.parseLibrary(libraries);
                    mDataBaseUtils.insertLibraries(newList);
                    for (Library library : newList) {
                        int index = getIndexItem(library.getObjectId());
                        if(index == -1)
                            list.add(0, library);
                        else
                            list.set(index,library);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("TAG", "Error: " + e.getMessage());
                }
            }
        });
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
