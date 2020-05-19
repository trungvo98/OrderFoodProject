package com.example.orderfood.models;

import java.io.Serializable;

public class Food implements Serializable {
    int id, id_foodType;
    String name;
    String price;
    String image;

    public Food() {
    }

    public Food(int id, int id_foodType, String name, String price, String image) {
        this.id = id;
        this.id_foodType = id_foodType;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_foodType() {
        return id_foodType;
    }

    public void setId_foodType(int id_foodType) {
        this.id_foodType = id_foodType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
