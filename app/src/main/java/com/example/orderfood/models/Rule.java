package com.example.orderfood.models;

import java.io.Serializable;

public class Rule implements Serializable {
    int id;
    String name;

    public Rule() {
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

}
