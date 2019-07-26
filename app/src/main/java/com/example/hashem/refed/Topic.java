package com.example.hashem.refed;

public class Topic {

    int id;
    String name;
    int ord;
    int secid;
    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ord=" + ord +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrd() {
        return ord;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }

    public Topic(int id, String name, int ord, int secid) {
        this.id = id;
        this.name = name;
        this.ord = ord;
        this.secid = secid;
    }
}
