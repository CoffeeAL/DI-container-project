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
    public boolean save(Event event) {
        return manager.getEvents().add(event);
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