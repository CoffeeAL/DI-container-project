package com.loiko.alex.service;

import com.loiko.alex.annotation.Inject;
import com.loiko.alex.dao.EventDao;
import com.loiko.alex.entity.Event;
import com.loiko.alex.exception.NotPositiveIdException;
import com.loiko.alex.exception.NullEventException;

import java.util.Optional;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public class EventServiceImpl implements EventService {

    private EventDao dao;

    @Inject
    public EventServiceImpl(EventDao dao) {
        this.dao = dao;
    }

    @Override
    public Event save(Event event) {
        if (event == null) {
            throw new NullEventException("Event not found");
        }
        return dao.save(event);
    }

    @Override
    public Optional<Event> getById(int id) {
        if (id <= 0) {
            throw new NotPositiveIdException("Id has to be positive value");
        }
        return dao.getById(id);
    }

    @Override
    public boolean deleteById(int id) {
        if (id <= 0) {
            throw new NotPositiveIdException("Id has to be positive value");
        }
        return dao.deleteById(id);
    }
}