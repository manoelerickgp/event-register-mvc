package com.project.eventregister.controllers;

import com.project.eventregister.models.Event;
import com.project.eventregister.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventController {

    @Autowired
    private EventRepository repository;

    @RequestMapping(value = "/eventRegister", method = RequestMethod.GET)
    public String form(){
        return "events/formEvent";
    }

    @RequestMapping(value = "/eventRegister", method = RequestMethod.POST)
    public String form(Event event) {
        repository.save(event);
        return "redirect:/eventRegister";
    }

    @RequestMapping(value = "/events")
    public ModelAndView eventList() {
        ModelAndView mv = new ModelAndView("index");
        Iterable<Event> events = repository.findAll();
        mv.addObject("event", events);
        return mv;
    }

    @RequestMapping(value = "/{id}")
    public ModelAndView eventDetails(@PathVariable Long id){
        Event event = repository.findById(id);
        ModelAndView mv = new ModelAndView("events/eventDetails");
        mv.addObject(event);
        return mv;
    }

}
