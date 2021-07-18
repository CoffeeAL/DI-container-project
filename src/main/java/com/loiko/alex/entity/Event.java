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
        id = ++COUNTER;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }
}