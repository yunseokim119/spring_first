package com.sparta.springfirstys.repository;

import com.sparta.springfirstys.entity.Event;

public interface EventRepository {
    void save(Event event);
    Event findById(Long id);
}