package com.example.charity_app.controller;

import com.example.charity_app.domain.FundraisingEvent;
import com.example.charity_app.service.FundraisingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class FundraisingEventController {
    @Autowired
    private FundraisingEventService eventService;
    
    @PostMapping
    public FundraisingEvent createEvent(@RequestBody FundraisingEvent event) {
        return eventService.createEvent(event);
    }
    
    @GetMapping
    public List<FundraisingEvent> getAllEvents() {
        return eventService.getAllEvents();
    }
    
    @GetMapping("/{id}")
    public FundraisingEvent getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }
}
