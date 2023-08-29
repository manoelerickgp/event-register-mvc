package com.project.eventregister.repositories;

import com.project.eventregister.models.Event;
import com.project.eventregister.models.Guest;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends CrudRepository<Guest, String> {
    Iterable<Guest> findByEvent(Event event);
}
