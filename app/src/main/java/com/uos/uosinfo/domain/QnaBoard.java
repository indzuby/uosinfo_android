package com.uos.uosinfo.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by user on 2016-01-20.
 */
@ParseClassName("QnABoard")
public class QnaBoard extends ParseObject{

    public String getQuestion() {
        return getString("question");
    }

    public void setQuestion(String question) {
        put("question",question);
    }

    public String getAnswer() {
        return getString("answer");
    }

    public void setAnswer(String answer) {
        put("answer",answer);
    }

}
