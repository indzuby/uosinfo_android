package com.uos.uosinfo.controller;

import android.content.Context;

import com.uos.uosinfo.domain.CareerResult;
import com.uos.uosinfo.domain.College;
import com.uos.uosinfo.domain.ResultPathFinder;
import com.uos.uosinfo.domain.Word;
import com.uos.uosinfo.utils.CodeDefinition;
import com.uos.uosinfo.utils.DataBaseUtils;
import com.uos.uosinfo.utils.JsonUtils;
import com.uos.uosinfo.utils.SessionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by 주현 on 2016-02-03.
 */
public class CareerFinderController {
    List<Word> mWords;
    boolean[] isWordsChecked;
    boolean[] isDup;
    Map<String,Integer> mWordCounter;
    int totalSelectedCount = 0;
    int beforeSelectedCount = 0;
    Map<String,Double> mColleges;
    Context mContext;
    DataBaseUtils mDataBaseUtils;
    public CareerFinderController(Context mContext){
        this.mContext = mContext;
        mDataBaseUtils = new DataBaseUtils(mContext);
        mColleges = new HashMap<>();
        mWordCounter = new HashMap<>();
        totalSelectedCount = 0;
        beforeSelectedCount = -1;
        mWords = mDataBaseUtils.getWords();
    }
    public void shuffleWord(){
        isDup  = new boolean[mWords.size()];
        List<Word> mCopyWords = new ArrayList<>();
        for(Word word : mWords)
            mCopyWords.add(word);
        while(mCopyWords.size()< CodeDefinition.CAREER_STEP_COUNT*CodeDefinition.CAREER_STEP_WORD_COUNT) {
            Random r = new Random();
            int k ;
            do {
                k = r.nextInt(mWords.size());
            }while(isDup[k]);
            isDup[k] = true;
            mCopyWords.add(mWords.get(k));
        }
        mWords = mCopyWords;
        isWordsChecked = new boolean[mWords.size()];
    }
    public List<Word> getNextWords(){
        List<Word> nextList = new ArrayList<>();
        for(int i = 0 ;i <CodeDefinition.CAREER_STEP_WORD_COUNT;i++) {
            Random r = new Random();
            int k ;
            do {
                k = r.nextInt(mWords.size());
                boolean flag = false;
                for(Word word : nextList) {
                    if(word.getObjectId().equalsIgnoreCase(mWords.get(k).getObjectId()))
                        flag = true;
                }
                if(flag) continue;
            }while(isWordsChecked[k]);
            isWordsChecked[k] = true;
            nextList.add(mWords.get(k));
        }
        return nextList;
    }
    public List<CareerResult> gotoResult(){
        for(Word word : mWords) {
            if(mWordCounter.containsKey(word.getObjectId())) {
                int count = mWordCounter.get(word.getObjectId());
                double point = 0;
                if(isDup[mWords.indexOf(word)]) {
                    if(count == 1)
                        point = 0.5;
                    else if(count == 2)
                        point = 1;
                }else {
                    if(count==1)
                        point = 1;
                }
                increasePoint(word.getCollege1(), point);
                increasePoint(word.getCollege2(), point);
            }
        }
        List<String> list = new ArrayList<>();
        list.addAll(mColleges.keySet());
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                int k =mColleges.get(rhs).compareTo(mColleges.get(lhs));
                if(k==0) {
                   if(mDataBaseUtils.getCollegeByObjectId(lhs).getDisplayOrder() > mDataBaseUtils.getCollegeByObjectId(rhs).getDisplayOrder())
                       k = 1;
                    else
                       k = -1;
                }
                return k;
            }
        });
        List<CareerResult> mAnswer = new ArrayList<>();
        double max = 0;
        int maxCount = 0 ;
        for(String key : list) {
            if(mColleges.get(key)>=CodeDefinition.CAREER_SCORE_HOLDER) {
                mAnswer.add(new CareerResult(key,mColleges.get(key)));
                if(max<mColleges.get(key)) {
                    max = mColleges.get(key);
                    maxCount = 1;
                }else if(max == mColleges.get(key))
                    maxCount++;
            }
        }
        if(max>=CodeDefinition.CAREER_SCORE_HOLDER && maxCount>=3) {
            mAnswer.clear();
            mAnswer.add(new CareerResult(CodeDefinition.CAREER_MANY_RESULT,0.0));
        }
        if(mAnswer.isEmpty())
            mAnswer.add(new CareerResult(CodeDefinition.CAREER_NO_RESULT,0.0));
        SessionUtils.putString(mContext,CodeDefinition.CAREER_RESULT_OBJECT,JsonUtils.objectToJson(mAnswer));
        return mAnswer;
    }
    private void increasePoint(College college, double score){
        if(college== null)
            return;
        if(mColleges.containsKey(college.getObjectId())) {
            score += mColleges.get(college.getObjectId());
            mColleges.remove(college.getObjectId());
        }
        mColleges.put(college.getObjectId(), score);
    }
    public int getWordCount(Word word){
        int count = 0;
        if(mWordCounter.containsKey(word.getObjectId())) {
            count = mWordCounter.get(word.getObjectId());
            mWordCounter.remove(word.getObjectId());
        }
        return count;
    }
    public void increaseWordCount(int count,Word word){
        count++;
        totalSelectedCount++;
        mWordCounter.put(word.getObjectId(), count);
    }
    public void decreaseWordCount(int count,Word word){
        count--;
        totalSelectedCount--;
        mWordCounter.put(word.getObjectId(), count);
    }
    public boolean isNotSelectedWord(){
        return beforeSelectedCount == totalSelectedCount;
    }
    public void setBeforeSelectedCountByTotalSelectedCount(){
        beforeSelectedCount = totalSelectedCount;
    }
    public List<CareerResult> getResultObjectList(){
        String resultJson = SessionUtils.getString(mContext,CodeDefinition.CAREER_RESULT_OBJECT,"{}");
        List<CareerResult> result = JsonUtils.JsonToResultList(resultJson);
        return result;
    }
    public College getCollege(String objectId) {
        return mDataBaseUtils.getCollegeByObjectId(objectId);
    }
    public ResultPathFinder getResultPathFinder(String objectId) {
        return mDataBaseUtils.getResultPathFinderByObjectId(objectId);
    }
    public List<ResultPathFinder> getResultPathFinderByCollege(String objectId) {
        return mDataBaseUtils.getResultPathFinderByCollege(objectId);
    }
}
