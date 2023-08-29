package com.project.eventregister.controllers;

import com.project.eventregister.models.Event;
import com.project.eventregister.models.Guest;
import com.project.eventregister.repositories.EventRepository;
import com.project.eventregister.repositories.GuestRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String form(@Valid Event event, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/eventRegister";
        }
        eventRepository.save(event);
        attributes.addFlashAttribute("mensagem", "Evento registrado com sucesso!");
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
        mv.addObject("event", event);

        Iterable<Guest> guets = guestRepository.findByEvent(event);
        mv.addObject("convidados", guets);

        return mv;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String insertGuest(@PathVariable Long id, @Valid Guest guest, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/{id}";
        }
        Event event = eventRepository.findById(id);
        guest.setEvent(event);
        guestRepository.save(guest);
        attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
        return "redirect:/{id}";
    }



}
