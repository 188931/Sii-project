package com.example.charity_app.domain;

import jakarta.persistence.*;
import java.math.BigDecimal; 

@Entity
public class FundraisingEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private BigDecimal amount;
    private String currency;
    
    public FundraisingEvent() {}
    
    public FundraisingEvent(String name, String currency) {
        this.name = name;
        this.currency = currency;
        this.amount = BigDecimal.ZERO;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}
