package com.loiko.alex.entity;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class Event {

    private static int COUNTER;
    private int id;
    private String description;

    public Event(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static int getCOUNTER() {
        return COUNTER;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}