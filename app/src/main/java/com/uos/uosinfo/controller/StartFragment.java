package com.uos.uosinfo.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.uos.uosinfo.R;
import com.uos.uosinfo.utils.CodeDefinition;
import com.uos.uosinfo.main.MainActivity;
import com.uos.uosinfo.main.UosFragment;
import com.uos.uosinfo.utils.SessionUtils;

/**
 * Created by user on 2015-12-30.
 */
public class StartFragment extends UosFragment implements View.OnClickListener{
    boolean isLast;
    int resource;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.element_start,container,false);
        isLast = false;
        isLast = getArguments().getBoolean("isLast");
        resource = getArguments().getInt("resource");
        init();
        return mView;
    }
    public void init(){

        ImageView imageView = (ImageView) mView.findViewById(R.id.splash_image);
        Glide.with(getActivity()).load(resource).into(imageView);
        imageView.setOnClickListener(null);
        Button button = (Button) mView.findViewById(R.id.start_button);
        if(isLast){
            button.setOnClickListener(this);
            imageView.setOnClickListener(this);
        }
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id) {
            case R.id.splash_image:
            case R.id.start_button:
                new CheckTypesTask().execute();
                break;
        }
    }
    private class CheckTypesTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog asyncDialog = new ProgressDialog(
                getActivity());

        @Override
        protected void onPreExecute() {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            asyncDialog.setMessage("데이터베이스 초기화 중입니다..");

            // show dialog
            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            int count;
            while((count = SessionUtils.getInt(getActivity(), CodeDefinition.DATABASE_SYNC, 0))<9){
                asyncDialog.setProgress(count * 100/9);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            asyncDialog.dismiss();
            gotoMain();
            super.onPostExecute(result);
        }
    }

    public void gotoMain(){
        SessionUtils.putBoolean(getActivity(), CodeDefinition.SPLASH_VIEW, true);
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
