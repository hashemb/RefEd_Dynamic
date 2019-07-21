package com.example.hashem.refed;

public class Module {
    private int id;
    private String name;
    private int lang;
    private String file;

    public Module(int id, String name, int lang, String file) {
        this.id = id;
        this.name = name;
        this.lang = lang;
        this.file = file;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLang(int lang) {
        this.lang = lang;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public int getLang() {
        return lang;
    }

    public String getFile() {
        return file;
    }

    public Module(int id, String name, int lang) {
        this.id = id;
        this.name = name;
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lang=" + lang +
                ", file='" + file + '\'' +
                '}';
    }
}
