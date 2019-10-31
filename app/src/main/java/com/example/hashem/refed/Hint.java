package com.example.hashem.refed;

public class Hint {
    String txt;
    String pic;

    @Override
    public String toString() {
        return "Hint{" +
                "txt='" + txt + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Hint(String txt, String pic) {
        this.txt = txt;
        this.pic = pic;
    }
}
