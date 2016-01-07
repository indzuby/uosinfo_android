package com.uos.uosinfo.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.uos.uosinfo.R;
import com.uos.uosinfo.adapter.LibraryAdapter;
import com.uos.uosinfo.domain.Library;

import java.util.List;

/**
 * Created by user on 2015-12-30.
 */
public class LibraryFragment extends Fragment {
    final String TAG = "Library Fragment";
    View mView;
    ListView mListView;
    LibraryAdapter adapter;
    List<Library> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_library,container,false);
        init();
        return mView;
    }

    private void init(){
        mListView = (ListView) mView.findViewById(R.id.listView);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Library");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> libraries, ParseException e) {
                if(e == null) {
                    Log.e("TAG",libraries.size()+"");
                    list = Library.PasreUtil.parseLibrary(libraries);
                    adapter = new LibraryAdapter(getActivity(),list);
                    mListView.setAdapter(adapter);
                }else {
                    Log.e("TAG","Error: "+e.getMessage());
                }
            }
        });
    }

}
