package com.example.hashem.refed;

public class TopicQuestion {

    int id;
    int topicid;
    String qtext;
    int type;
    int ord;

    @Override
    public String toString() {
        return "TopicQuestion{" +
                "id=" + id +
                ", topicid=" + topicid +
                ", qtext='" + qtext + '\'' +
                ", type=" + type +
                ", ord=" + ord +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTopicid() {
        return topicid;
    }

    public void setTopicid(int topicid) {
        this.topicid = topicid;
    }

    public String getQtext() {
        return qtext;
    }

    public void setQtext(String qtext) {
        this.qtext = qtext;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOrd() {
        return ord;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }

    public TopicQuestion(int id, int topicid, String qtext, int type, int ord) {

        this.id = id;
        this.topicid = topicid;
        this.qtext = qtext;
        this.type = type;
        this.ord = ord;
    }
}
