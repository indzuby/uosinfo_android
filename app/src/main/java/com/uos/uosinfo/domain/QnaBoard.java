package com.uos.uosinfo.domain;

import com.parse.ParseClassName;

/**
 * Created by user on 2016-01-20.
 */
@ParseClassName("QnABoard")
public class QnaBoard extends Common{

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
    public String getUserId() {
        return getString("userId");
    }
    public void setUsetId(String usetId) { put("userId",usetId);}

}
