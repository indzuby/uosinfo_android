package com.uos.uosinfo.view.pathfinder;

import android.content.Intent;
import android.net.Uri;
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
import com.uos.uosinfo.view.PathFinderFragment;
import com.uos.uosinfo.domain.PathFinder;
import com.uos.uosinfo.utils.DataBaseUtils;

/**
 * Created by 주현 on 2016-01-10.
 */
public class PathFinderItemFragment extends Fragment implements View.OnClickListener,PathFinderInterface {
    private ImageButton mFloatingArrow;
    View mView;
    PathFinder mPath;
    String objectId;
    private boolean language = false; // false : En , true : Ko;

    FloatingArrowPopup mfFloatingArrowPopup;
    TextView mTitle,mField,mCollege,mWikiEn,mWikiKo,mBook;
    DataBaseUtils mDataBaseUtils;
    public PathFinderItemFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.element_path_finder_card, container, false);
        mDataBaseUtils = new DataBaseUtils(getContext());
        init();
        return mView;
    }
    private void init(){
        mFloatingArrow = (ImageButton) mView.findViewById(R.id.arrow_button);
        mFloatingArrow.setOnClickListener(this);
        objectId = getArguments().getString("objectId");
        mPath=mDataBaseUtils.getPathFinderByObjectId(objectId);
        language = ((PathFinderFragment)getParentFragment()).isLanguage();
        initHeader();
        initBody();
        setLanguage(language);
    }
    private void initHeader(){

        ImageView image = (ImageView) mView.findViewById(R.id.path_finder_image);
        mTitle = (TextView) mView.findViewById(R.id.path_finder_title);
        TextView person = (TextView) mView.findViewById(R.id.path_finder_person);

        Glide.with(getActivity())
                .load(mPath.getCelebrity().getImage())
                .fitCenter().crossFade().into(image);
        person.setText(mPath.getCelebrity().getName());

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
    @Override
    public void setLanguage(boolean language){
        if(language) {
            mTitle.setText(mPath.getCelebrity().getTitleKo());
            mCollege.setText(mPath.getCelebrity().getCollege().getName());
            mField.setText(mPath.getCelebrity().getFieldKo());
            mWikiKo.setText("위키백과(국문)");
            mWikiEn.setText("위키백과(영문)");
            mBook.setText("구글북스 검색결과");
        }else {
            mTitle.setText(mPath.getCelebrity().getTitleEn());
            mCollege.setText(mPath.getCelebrity().getCollege().getNameEn());
            mField.setText(mPath.getCelebrity().getFieldEn());
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
                    ((PathFinderFragment)getParentFragment()).changeLanguage();
                    break;
                case R.id.bg_img_button:
                case R.id.bg_button:
                    ((PathFinderFragment)getParentFragment()).changeBg();
                    break;
                case R.id.last_img_button:
                case R.id.last_button:
                    ((PathFinderFragment)getParentFragment()).lastPathFinder();
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
                mfFloatingArrowPopup.setLastButton(((PathFinderFragment) getParentFragment()).isThisMonth());
                break;
            case R.id.path_finder_wiki_en:
                uri = Uri.parse(mPath.getCelebrity().getWikiEn());
                launchBrowser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(launchBrowser);
                break;
            case R.id.path_finder_wiki_ko:
                uri = Uri.parse(mPath.getCelebrity().getWikiKo());
                launchBrowser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(launchBrowser);
                break;
            case R.id.path_finder_book:
                uri = Uri.parse(mPath.getCelebrity().getBook());
                launchBrowser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(launchBrowser);
                break;
        }
    }

}
