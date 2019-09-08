package com.example.hashem.refed;

public class Answer {

    int id;
    int objectid;
    String atext;
    int ord;
    int correct;

    public Answer(int id, int objectid, String atext, int ord, int correct) {
        this.id = id;
        this.objectid = objectid;
        this.atext = atext;
        this.ord = ord;
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", objectid=" + objectid +
                ", atext='" + atext + '\'' +
                ", ord=" + ord +
                ", correct=" + correct +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getObjectid() {
        return objectid;
    }

    public void setObjectid(int objectid) {
        this.objectid = objectid;
    }

    public String getAtext() {
        return atext;
    }

    public void setAtext(String atext) {
        this.atext = atext;
    }

    public int getOrd() {
        return ord;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }
}
