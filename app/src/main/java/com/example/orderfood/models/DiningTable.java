package com.example.orderfood.models;

import java.io.Serializable;

public class DiningTable implements Serializable {
    int id;
    String name;
    boolean isChoose;

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

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public DiningTable() {
    }

    public DiningTable(int id, String name, boolean isChoose) {
        this.id = id;
        this.name = name;
        this.isChoose = isChoose;
    }
}
