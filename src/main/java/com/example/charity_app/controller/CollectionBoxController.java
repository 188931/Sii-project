package com.example.charity_app.controller;

import com.example.charity_app.domain.CollectionBox;
import com.example.charity_app.domain.FundraisingEvent;
import com.example.charity_app.service.CollectionBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/boxes")
public class CollectionBoxController {
    @Autowired
    private CollectionBoxService boxService;
    
    @PostMapping
    public CollectionBox registerBox(@RequestBody CollectionBox box) {
        return boxService.registerBox(box);
    }
    
    @GetMapping
    public List<CollectionBox> getAllBoxes() {
        return boxService.getAllBoxes();
    }
    
    @GetMapping("/{id}")
    public CollectionBox getBoxById(@PathVariable Long id) {
        return boxService.getBoxById(id);
    }
    
    @DeleteMapping("/{id}")
    public void unregisterBox(@PathVariable Long id) {
        boxService.unregisterBox(id);
    }
    
    @PostMapping("/{boxId}/assign/{eventId}")
    public CollectionBox assignToEvent(@PathVariable Long boxId, @PathVariable Long eventId) {
        return boxService.assignToEvent(boxId, eventId);
    }
    
    @PostMapping("/{boxId}/add-money")
    public CollectionBox addMoney(@PathVariable Long boxId, 
                                @RequestParam String currency, 
                                @RequestParam BigDecimal amount) {
        return boxService.addMoney(boxId, currency, amount);
    }
    
    @PostMapping("/{boxId}/empty")
    public FundraisingEvent emptyBox(@PathVariable Long boxId) {
        return boxService.emptyBox(boxId);
    }
}
