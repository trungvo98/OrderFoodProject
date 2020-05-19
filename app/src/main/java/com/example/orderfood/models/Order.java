package com.example.orderfood.models;

import java.io.Serializable;

public class Order implements Serializable {
    int id, employID, tableID;
    String status, dateOrder;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployID() {
        return employID;
    }

    public void setEmployID(int employID) {
        this.employID = employID;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }
}
