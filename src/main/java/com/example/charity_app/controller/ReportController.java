package com.example.charity_app.controller;

import com.example.charity_app.domain.FundraisingEvent;
import com.example.charity_app.service.FundraisingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private FundraisingEventService eventService;
    
    @GetMapping("/financial")
    public List<FundraisingEvent> getFinancialReport() {
        return eventService.getAllEvents();
    }
}
