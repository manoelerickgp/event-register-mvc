package com.project.eventregister.controllers;

import com.project.eventregister.models.Event;
import com.project.eventregister.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EventController {

    @Autowired
    private EventRepository repository;

    @RequestMapping(value = "/eventRegister", method = RequestMethod.GET)
    public String form(){
        return "events/formEvent";
    }

    @RequestMapping(value = "/eventRegister", method = RequestMethod.POST)
    public String form(Event obj) {
        repository.save(obj);
        return "redirect:/eventRegister";
    }
}
