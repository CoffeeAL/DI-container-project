package com.loiko.alex.manager;

import com.loiko.alex.entity.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class EventManager {

    private List<Event> events = new ArrayList<>();

    public List<Event> getEvents() {
        return events;
    }
}