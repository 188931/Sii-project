package com.example.charity_app.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Entity
public class CollectionBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String identifier;
    private boolean isEmpty;
    
    @ElementCollection
    @CollectionTable(name = "box_currencies", joinColumns = @JoinColumn(name = "box_id"))
    @MapKeyColumn(name = "currency")
    @Column(name = "amount")
    private Map<String, BigDecimal> currencies = new HashMap<>();
    
    @ManyToOne
    private FundraisingEvent fundraisingEvent;
    
    // Constructors, getters, and setters
    public CollectionBox() {
        this.isEmpty = true;
    }
    
    public CollectionBox(String identifier) {
        this.identifier = identifier;
        this.isEmpty = true;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIdentifier() { return identifier; }
    public void setIdentifier(String identifier) { this.identifier = identifier; }
    public boolean isEmpty() { return isEmpty; }
    public void setEmpty(boolean empty) { isEmpty = empty; }
    public Map<String, BigDecimal> getCurrencies() { return currencies; }
    public void setCurrencies(Map<String, BigDecimal> currencies) { this.currencies = currencies; }
    public FundraisingEvent getFundraisingEvent() { return fundraisingEvent; }
    public void setFundraisingEvent(FundraisingEvent fundraisingEvent) { this.fundraisingEvent = fundraisingEvent; }
}
