package com.example.countbook;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chias
 */

/**
 * Represents a counter object
 */
public class Counter {
    private String name;
    private Date date;
    private int initialValue;
    private int currentValue;
    private String comment;

    /**
     * Contructs a counter without a comment
     * @param name counter name
     * @param value counter initial and current values
     */
    public Counter(String name, int value) {
        this.name = name;
        this.date = new Date();
        this.initialValue = value;
        this.currentValue = value;
    }

    /**
     * Constructs a counter with a comment
     * @param name counter name
     * @param value counter initial and current values
     * @param comment counter comment
     */
    public Counter(String name, int value, String comment) {
        this.name = name;
        this.date = new Date();
        this.initialValue = value;
        this.currentValue = value;
        this.comment = comment;
    }

    /**
     * Gets Counter's name
     * @return counter name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets Counter's comment
     * @return counter comment
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * Gets Counter's date
     * @return counter date
     */
    public String getDate() {
        SimpleDateFormat properFormat = new SimpleDateFormat("yyyy-MM-dd");
        return properFormat.format(this.date);
    }

    /**
     * Updates Counter's date
     */
    public void setDate() {
        this.date = new Date();
    }

    /**
     * Gets Counter's current value
     * @return counter current value
     */
    public int getCurrentValue() {
        return this.currentValue;
    }

    /**
     * Gets Counter's intial value
     * @return counter initial value
     */
    public int getInitialValue() {
        return this.initialValue;
    }

    /**
     * Changes counter's current value by +/- 1
     * @param increment +/- 1
     */
    public void changeCount(int increment) {
        if (this.currentValue == 0 && increment<0) {
            this.currentValue = 0;
        }
        else {
            this.currentValue = this.currentValue + increment;
            this.date = new Date();
        }
    }

    /**
     * Resets Counter's current value to initial value and updates date
     */
    public void resetCount() {
        this.currentValue = this.initialValue;
        this.date = new Date();
    }

    /**
     * Edits Counter's name
     * @param newName new counter name
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Edits Counter's comment
     * @param newComment new counter comment
     */
    public void setComment(String newComment) {
        this.comment = newComment;
    }

    /**
     * Edits initial value of Counter
     * @param newInitial new initial value
     */
    public void setInitialValue(int newInitial) {
        this.initialValue = newInitial;
    }

    /**
     * Edits current value of Counter
     * @param newCurrent new current value
     */
    public void setCurrentValue(int newCurrent) {
        this.currentValue = newCurrent;
    }

    /**
     * Allows Counter summary to be printable in ListView
     * @return counter summary
     */
    @Override
    public String toString() {
        SimpleDateFormat properFormat = new SimpleDateFormat("yyyy-MM-dd");
        return name+" | Current Value: "+currentValue+" | Initial Value: "+initialValue+" \n"+properFormat.format(date);
    }

}
