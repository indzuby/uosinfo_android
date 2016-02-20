package com.uos.uosinfo.utils;

import android.content.Context;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.uos.uosinfo.domain.Admission;
import com.uos.uosinfo.domain.College;
import com.uos.uosinfo.domain.Department;
import com.uos.uosinfo.domain.DepartmentImage;
import com.uos.uosinfo.domain.Library;
import com.uos.uosinfo.domain.Notice;
import com.uos.uosinfo.domain.PathFinder;
import com.uos.uosinfo.domain.QnaBoard;
import com.uos.uosinfo.domain.Recruitment;
import com.uos.uosinfo.domain.RecruitmentType;
import com.uos.uosinfo.domain.ResultPathFinder;
import com.uos.uosinfo.domain.Word;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 2016-01-12.
 */
public class DataBaseUtils {
    Context mContext;

    public DataBaseUtils(Context context) {
        this.mContext = context;
    }

    public List<Library> getMoreLibrary(Date lastDate) {
        ParseQuery<Library> query = ParseQuery.getQuery(Library.class);
        if (lastDate == null) {
            query = query.orderByAscending("createdAt");
        } else {
            query = query.whereGreaterThanOrEqualTo("updatedAt", lastDate).orderByAscending("createdAt");
        }
        try {
            return query.find();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Library> getLibraries() {
        ParseQuery<Library> query = ParseQuery.getQuery(Library.class);
        query = query.whereEqualTo("valid",true).orderByDescending("createdAt");
        query.fromPin();
        try {
            return query.find();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    public Date getLastLibrary(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Library");
        return getLastDataBase(query);
    }
    public Date getLastQna(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("QnABoard");
        return getLastDataBase(query);
    }
    public Date getLastDataBase(ParseQuery<ParseObject> query){
        query = query.whereEqualTo("valid",true).orderByDescending("updatedAt");
        query.fromPin();
        try {
            return query.find().get(0).getUpdatedAt();
        } catch (Exception e) {
            return new Date();
        }

    }

    public List<Notice> getNotices() {
        ParseQuery<Notice> query = ParseQuery.getQuery(Notice.class);
        query = query.whereEqualTo("valid",true);
        query = query.orderByDescending("createdAt");
        query.fromPin();
        try {
            return query.find();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    public Date getLastNotice(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Notice");
        return getLastDataBase(query);
    }
    public List<Notice> getMoreNotice(Date lastDate) {
        ParseQuery<Notice> query = ParseQuery.getQuery(Notice.class);
        if (lastDate == null) {
            query = query.orderByAscending("createdAt");
        } else {
            query = query.whereGreaterThanOrEqualTo("updatedAt", lastDate).orderByAscending("createdAt");
        }
        try {
            return query.find();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    public List<QnaBoard> getMoreQna(Date lastDate) {
        ParseQuery<QnaBoard> query = ParseQuery.getQuery(QnaBoard.class);
        if (lastDate == null) {
            query = query.orderByAscending("createdAt");
        } else {
            query = query.whereGreaterThanOrEqualTo("updatedAt", lastDate).orderByAscending("createdAt");
        }
        try {
            return query.find();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    public List<PathFinder> getPathFinderByParse(Date date) {
        ParseQuery<PathFinder> query = ParseQuery.getQuery(PathFinder.class);
        query = query.whereEqualTo("valid",true);
        try {
            return query.fromPin().whereLessThanOrEqualTo("startDatetime", date).whereGreaterThanOrEqualTo("endDatetime", date).orderByAscending("displayOrder").find();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public PathFinder getPathFinderByObjectId(String objectId) {
        ParseQuery<PathFinder> query = ParseQuery.getQuery(PathFinder.class);
        query = query.whereEqualTo("valid",true);
        query.fromPin();
        query.include("Celebrity");
        try {
            return query.get(objectId);
        } catch (Exception e) {
            return null;
        }
    }
    public ResultPathFinder getResultPathFinderByObjectId(String objectId) {
        ParseQuery<ResultPathFinder> query = ParseQuery.getQuery(ResultPathFinder.class);
        query = query.whereEqualTo("valid",true);
        query.fromPin();
        query.include("Celebrity");
        try {
            return query.get(objectId);
        } catch (Exception e) {
            return null;
        }
    }
    public List<ResultPathFinder> getResultPathFinderByCollege(String objectId) {
        ParseQuery<ResultPathFinder> query = ParseQuery.getQuery(ResultPathFinder.class);
        ParseQuery<College> innerQuery = ParseQuery.getQuery(College.class);
        innerQuery.whereEqualTo("objectId",objectId);
        query = query.whereEqualTo("valid",true);
        query.fromPin().whereMatchesQuery("college",innerQuery);
        query.include("Celebrity");
        query.include("College");
        try {
            return query.find();
        } catch (Exception e) {
            return null;
        }
    }
    public List<Word> getWords(){
        ParseQuery<Word> query = ParseQuery.getQuery(Word.class);
        query = query.whereEqualTo("valid",true);
        try {
            return query.fromPin().find();
        } catch (Exception e) {
            return new ArrayList<>();
        }

    }

    public void getMoreDatabase(Date date, ParseQuery query) {
        query.whereGreaterThan("updatedAt", date).findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                try {
                    ParseObject.pinAll(objects);
                    int count = SessionUtils.getInt(mContext, CodeDefinition.DATABASE_SYNC,0);
                    SessionUtils.putInt(mContext,CodeDefinition.DATABASE_SYNC,count+1);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }
    public College getCollegeByObjectId(String objectId){
        ParseQuery<College> query = ParseQuery.getQuery(College.class);
        query = query.whereEqualTo("valid",true);
        try {
            return query.fromPin().get(objectId);
        } catch (Exception e) {
            return null;
        }

    }

    public List<College> getColleges(){
        ParseQuery<College> query = ParseQuery.getQuery(College.class);
        query = query.whereEqualTo("valid",true);
        query.orderByAscending("displayOrder");
        try {
            return query.find();
        } catch (Exception e) {
            return null;
        }

    }
    public List<Department> getDepartmentByCollege(String objectId) {
        ParseQuery<Department> query = ParseQuery.getQuery(Department.class);
        ParseQuery<College> innerQuery = ParseQuery.getQuery(College.class);
        query = query.whereEqualTo("valid",true);
        innerQuery.whereEqualTo("objectId",objectId);
        query.fromPin().whereMatchesQuery("college",innerQuery);
        query.include("College");
        try {
            return query.find();
        } catch (Exception e) {
            return null;
        }
    }
    public DepartmentImage getDepartmentImageByDepartment(String objectId) {
        ParseQuery<DepartmentImage> query = ParseQuery.getQuery(DepartmentImage.class);
        ParseQuery<Department> innerQuery = ParseQuery.getQuery(Department.class);
        innerQuery.whereEqualTo("objectId", objectId);
        query = query.whereEqualTo("valid",true);
        query.fromPin().whereMatchesQuery("department", innerQuery);
        try {
            return query.find().get(0);
        } catch (Exception e) {
            return null;
        }
    }
    public Admission getAdmissionByDepartment(String objectId) {
        ParseQuery<Admission> query = ParseQuery.getQuery(Admission.class);
        ParseQuery<Department> innerQuery = ParseQuery.getQuery(Department.class);
        query = query.whereEqualTo("valid",true);
        innerQuery.whereEqualTo("objectId", objectId);
        query.fromPin().whereMatchesQuery("department", innerQuery);
        try {
            return query.find().get(0);
        } catch (Exception e) {
            return null;
        }
    }
    public List<Recruitment> getRecruitmentByAdmission(String objectId) {
        ParseQuery<Recruitment> query = ParseQuery.getQuery(Recruitment.class);
        ParseQuery<Admission> innerQuery = ParseQuery.getQuery(Admission.class);
        innerQuery.whereEqualTo("objectId", objectId);
        query = query.whereEqualTo("valid",true);
        query.fromPin().whereMatchesQuery("admission", innerQuery);
        try {
            return query.find();
        } catch (Exception e) {
            return null;
        }
    }
    public RecruitmentType getRecruitmentTypeByObjectId(String objectId){
        ParseQuery<RecruitmentType> query = ParseQuery.getQuery(RecruitmentType.class);
        query = query.whereEqualTo("valid",true);
        try {
            return query.fromPin().get(objectId);
        } catch (Exception e) {
            return null;
        }

    }
    public List<QnaBoard> getQna(){
        ParseQuery<QnaBoard> query = ParseQuery.getQuery(QnaBoard.class);
        query = query.whereEqualTo("valid",true).orderByAscending("createdAt");
        try {
            return query.fromPin().find();
        } catch (Exception e) {
            return null;
        }
    }
    public QnaBoard sendQna(String qna,String token){
        QnaBoard qnaBoard =new QnaBoard();
        qnaBoard.setQuestion(qna);
        qnaBoard.setUsetId(token);
        qnaBoard.setValid(true);
        qnaBoard.saveInBackground();
        return qnaBoard;
    }

}
