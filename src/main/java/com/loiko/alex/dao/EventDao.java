package com.loiko.alex.dao;

import com.loiko.alex.entity.Event;

import java.util.Optional;

/**
 * @author Alexey Loiko
 * @project DIcontainerproject
 */
public interface EventDao {

    boolean save(Event event);

    Optional<Event> getById(int id);

    boolean deleteById(int id);
}