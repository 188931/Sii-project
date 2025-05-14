package com.example.charity_app.service;

import com.example.charity_app.domain.FundraisingEvent;
import com.example.charity_app.repository.FundraisingEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundraisingEventService {
    @Autowired
    private FundraisingEventRepository fundraisingEventRepository;
    
    public FundraisingEvent createEvent(FundraisingEvent event) {
        return fundraisingEventRepository.save(event);
    }
    
    public List<FundraisingEvent> getAllEvents() {
        return fundraisingEventRepository.findAll();
    }
    
    public FundraisingEvent getEventById(Long id) {
        return fundraisingEventRepository.findById(id).orElse(null);
    }
}