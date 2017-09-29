package com.example.countbook;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chias on 2017-09-21.
 */

public class Counter {
    private String name;
    private Date date;
    private int initialValue;
    private int currentValue;
    private String comment;
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

    public Counter(String name, int value, String comment) {
        this.name = name;
        this.date = new Date();
        this.initialValue = value;
        this.currentValue = value;
        this.comment = comment;
    }
    public String getName() {
        return this.name;
    }

    public String getComment() {
        return this.comment;
    }

    public String getDate() {
        SimpleDateFormat properFormat = new SimpleDateFormat("yyyy-MM-dd");
        return properFormat.format(this.date);
    }

    public void setDate() {
        this.date = new Date();
    }

    public int getCurrentValue() {
        return this.currentValue;
    }

    public int getInitialValue() {
        return this.initialValue;
    }

    public void changeCount(int increment) {
        if (this.currentValue == 0 && increment<0) {
            this.currentValue = 0;
        }
        else {
            this.currentValue = this.currentValue + increment;
            this.date = new Date();
        }
    }
    public void resetCount() {
        this.currentValue = this.initialValue;
        this.date = new Date();
    }
    public void setName(String newName) {
        this.name = newName;
    }
    public void setComment(String newComment) {
        this.comment = newComment;
    }
    public void setInitialValue(int newInitial) {
        this.initialValue = newInitial;
    }
    public void setCurrentValue(int newCurrent) {
        this.currentValue = newCurrent;
    }

    @Override
    public String toString() {
        SimpleDateFormat properFormat = new SimpleDateFormat("yyyy-MM-dd");
        return name+" | Current Value: "+currentValue+" | Initial Value: "+initialValue+" \n"+properFormat.format(date);
    }

}
