package com.project.eventregister.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventController {

    @RequestMapping(value = "/eventRegister")
    public String form(){
        return "events/formEvent";
    }
}
