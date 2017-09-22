package com.example.countbook;

import java.util.Date;

/**
 * Created by chias on 2017-09-21.
 */

public class Counter {
    private String name;
    private Date date;
    private int initialValue;
    private int currentValue;
    public Counter(String name, int value) {
        this.name = name;
        this.date = new Date();
        this.initialValue = value;
        this.currentValue = value;
    }

    public Counter(String name,int value, Date date) {
        this.name = name;
        this.date = date;
        this.initialValue = value;
        this.currentValue = value;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void changeCount(int increment) {
        this.currentValue = this.currentValue+increment;
        this.date = new Date();
    }
    public void resetCount() {
        this.currentValue = this.initialValue;
        this.date = new Date();
    }
    @Override
    public String toString() {
        return name+" | "+currentValue+" | "+initialValue+" | "+date.toString();
    }
}
