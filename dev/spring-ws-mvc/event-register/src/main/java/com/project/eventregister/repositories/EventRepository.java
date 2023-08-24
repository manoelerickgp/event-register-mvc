package com.project.eventregister.repositories;

import com.project.eventregister.models.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, String> {
    Event findById(Long id);
}
