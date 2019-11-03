package com.example.hashem.refed.Models;

public class Content {

    int id;
    int topicid;
    String qtext;
    String file;
    int ord;
    int type;
    int qtype;
    String hint;
    String hintpic;


    public Content(int id, int topicid, String file, int ord, int type) {
        // a video
        this.id = id;
        this.topicid = topicid;
        this.file = file;
        this.ord = ord;
        this.type = type;
    }

    public Content() {
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", topicid=" + topicid +
                ", qtext='" + qtext + '\'' +
                ", file='" + file + '\'' +
                ", ord=" + ord +
                ", type=" + type +
                ", qtype=" + qtype +
                ", hint='" + hint + '\'' +
                ", hintpic='" + hintpic + '\'' +
                '}';
    }

    public Content(int id, int topicid, String qtext, String file, int ord, int type, int qtype, String hint, String hintpic) {
        // question with a hint
        this.id = id;
        this.topicid = topicid;
        this.qtext = qtext;
        this.file = file;
        this.ord = ord;

        this.type = type;
        this.qtype = qtype;
        this.hint = hint;
        this.hintpic = hintpic;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getOrd() {
        return ord;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getQtype() {
        return qtype;
    }

    public void setQtype(int qtype) {
        this.qtype = qtype;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getHintpic() {
        return hintpic;
    }

    public void setHintpic(String hintpic) {
        this.hintpic = hintpic;
    }
}
