package com.loiko.alex.service;

import com.loiko.alex.entity.Event;

import java.util.Optional;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public interface EventService {

    boolean save(Event event);

    Optional<Event> getById(int id);

    boolean deleteById(int id);
}