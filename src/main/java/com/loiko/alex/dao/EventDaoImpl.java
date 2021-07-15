package com.loiko.alex.dao;

import com.loiko.alex.annotation.Inject;
import com.loiko.alex.entity.Event;
import com.loiko.alex.manager.EventManager;

import java.util.Optional;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class EventDaoImpl implements EventDao {

    private EventManager manager;

    @Inject
    public EventDaoImpl(EventManager manager) {
        this.manager = manager;
    }

    @Override
    public Event save(Event event) {
        int counter = Event.getCOUNTER();
        int id = ++counter;
        event.setId(id);
        return event;
    }

    @Override
    public Optional<Event> getById(int eventId) {
        return manager.getEvents().stream()
                .filter(event -> event.getId() == eventId)
                .findFirst();
    }

    @Override
    public boolean deleteById(int eventId) {
        Optional<Event> maybeEvent = manager.getEvents().stream()
                .filter(event -> event.getId() == eventId)
                .findFirst();
        return manager.getEvents().remove(maybeEvent);
    }
}