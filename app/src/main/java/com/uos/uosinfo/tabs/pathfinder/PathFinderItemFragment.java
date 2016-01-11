package com.uos.uosinfo.tabs.pathfinder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uos.uosinfo.R;
import com.uos.uosinfo.domain.PathFinder;
import com.uos.uosinfo.tabs.PathFinderFragment;
import com.uos.uosinfo.utils.BgUtils;
import com.uos.uosinfo.utils.JsonUtils;

/**
 * Created by 주현 on 2016-01-10.
 */
public class PathFinderItemFragment extends Fragment implements View.OnClickListener{
    private ImageButton mFloatingArrow;
    View mView;
    PathFinder mPass;
    private boolean language = false; // false : En , true : Ko;
    FloatingArrowPopup mfFloatingArrowPopup;
    TextView mTitle,mField,mCollege,mWikiEn,mWikiKo,mBook;
    public PathFinderItemFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.element_path_finder_card, container, false);
        init();
        return mView;
    }
    private void init(){
        mFloatingArrow = (ImageButton) mView.findViewById(R.id.arrow_button);
        mFloatingArrow.setOnClickListener(this);
        mPass = JsonUtils.JsonToObject(getArguments().getString("passFinder"),PathFinder.class);

        initHeader();
        initBody();
        setLanguage();
    }
    private void initHeader(){

        ImageView image = (ImageView) mView.findViewById(R.id.path_finder_image);
        mTitle = (TextView) mView.findViewById(R.id.path_finder_title);
        TextView person = (TextView) mView.findViewById(R.id.path_finder_person);
        Glide.with(getActivity()).load(mPass.getImage()).fitCenter().crossFade().into(image);
        person.setText(mPass.getName());

    }

    private void initBody(){
        mCollege = (TextView) mView.findViewById(R.id.path_finder_college);
        mField = (TextView) mView.findViewById(R.id.path_finder_field);
        mWikiEn = (TextView) mView.findViewById(R.id.path_finder_wiki_en);
        mWikiKo = (TextView) mView.findViewById(R.id.path_finder_wiki_ko);
        mBook = (TextView) mView.findViewById(R.id.path_finder_book);
        mWikiEn.setOnClickListener(this);
        mWikiKo.setOnClickListener(this);
        mBook.setOnClickListener(this);
    }

    private void setLanguage(){
        if(language) {
            mTitle.setText(mPass.getKo().getTitle());
            mCollege.setText(mPass.getKo().getCollege());
            mField.setText(mPass.getKo().getField());
            mWikiKo.setText("위키백과(국문)");
            mWikiEn.setText("위키백과(영문)");
            mBook.setText("구글북스 검색결과");
        }else {
            mTitle.setText(mPass.getEn().getTitle());
            mCollege.setText(mPass.getEn().getCollege());
            mField.setText(mPass.getEn().getField());
            mWikiKo.setText("wikipedia.org(ko)");
            mWikiEn.setText("wikipedia.org(en)");
            mBook.setText("Google Books Search Results");
        }
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.translate_img_button:
                case R.id.translate_button:
                    language = !language;
                    setLanguage();

                    break;
                case R.id.bg_img_button:
                case R.id.bg_button:
                    ((PathFinderFragment)getParentFragment()).changeBg();
                    break;
                case R.id.last_img_button:
                case R.id.last_button:
                    break;
            }
            mfFloatingArrowPopup.dismiss();
        }
    };
    @Override
    public void onClick(View v) {
        int id = v.getId();
        Uri uri;
        Intent launchBrowser;
        switch (id) {
            case R.id.arrow_button:
                mfFloatingArrowPopup = new FloatingArrowPopup(getContext(),listener);
                mfFloatingArrowPopup.show();
                break;
            case R.id.path_finder_wiki_en:
                uri = Uri.parse(mPass.getEn().getWiki());
                launchBrowser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(launchBrowser);
                break;
            case R.id.path_finder_wiki_ko:
                uri = Uri.parse(mPass.getKo().getWiki());
                launchBrowser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(launchBrowser);
                break;
            case R.id.path_finder_book:
                uri = Uri.parse(mPass.getBook());
                launchBrowser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(launchBrowser);
                break;
        }
    }

}
