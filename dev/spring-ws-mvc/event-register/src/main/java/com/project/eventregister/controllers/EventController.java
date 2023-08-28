package com.project.eventregister.controllers;

import com.project.eventregister.models.Event;
import com.project.eventregister.models.Guest;
import com.project.eventregister.repositories.EventRepository;
import com.project.eventregister.repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private GuestRepository guestRepository;

    @RequestMapping(value = "/eventRegister", method = RequestMethod.GET)
    public String form(){
        return "events/formEvent";
    }

    @RequestMapping(value = "/eventRegister", method = RequestMethod.POST)
    public String form(Event event) {
        eventRepository.save(event);
        return "redirect:/eventRegister";
    }

    @RequestMapping(value = "/events")
    public ModelAndView eventList() {
        ModelAndView mv = new ModelAndView("index");
        Iterable<Event> events = eventRepository.findAll();
        mv.addObject("event", events);
        return mv;
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public ModelAndView eventDetails(@PathVariable Long id){
        Event event = eventRepository.findById(id);
        ModelAndView mv = new ModelAndView("events/eventDetails");
        mv.addObject(event);
        return mv;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String insertGuest(@PathVariable Long id, Guest guest) {
        Event event = eventRepository.findById(id);
        guest.setEvent(event);
        guestRepository.save(guest);
        return "redirect:/{id}";
    }

}
