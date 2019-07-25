package com.example.hashem.refed;

public class Section {
    int id;
    int modid;
    String name;
    int order;


    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", modid=" + modid +
                ", name='" + name + '\'' +
                ", order=" + order +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModid() {
        return modid;
    }

    public void setModid(int modid) {
        this.modid = modid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Section(int id, int modid, String name, int order) {

        this.id = id;
        this.modid = modid;
        this.name = name;
        this.order = order;
    }
}
