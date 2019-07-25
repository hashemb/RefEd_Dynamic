package com.example.hashem.refed;

public class TopicVideo {

    int id;
    int topicid;
    String file;
    int ord;

    @Override
    public String toString() {
        return "TopicVideo{" +
                "id=" + id +
                ", topicid=" + topicid +
                ", file='" + file + '\'' +
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

    public TopicVideo(int id, int topicid, String file, int ord) {

        this.id = id;
        this.topicid = topicid;
        this.file = file;
        this.ord = ord;
    }
}
