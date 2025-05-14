package com.example.charity_app.service;

import com.example.charity_app.domain.CollectionBox;
import com.example.charity_app.domain.FundraisingEvent;
import com.example.charity_app.repository.CollectionBoxRepository;
import com.example.charity_app.repository.FundraisingEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class CollectionBoxService {
    @Autowired
    private CollectionBoxRepository collectionBoxRepository;
    
    @Autowired
    private FundraisingEventRepository fundraisingEventRepository;
    
    @Autowired
    private CurrencyExchangeService currencyExchangeService;
    
    public CollectionBox registerBox(CollectionBox box) {
        box.setEmpty(true);
        return collectionBoxRepository.save(box);
    }
    
    public List<CollectionBox> getAllBoxes() {
        return collectionBoxRepository.findAll();
    }
    
    public CollectionBox getBoxById(Long id) {
        return collectionBoxRepository.findById(id).orElse(null);
    }
    
    public CollectionBox getBoxByIdentifier(String identifier) {
        return collectionBoxRepository.findByIdentifier(identifier);
    }
    
    @Transactional
    public void unregisterBox(Long id) {
        CollectionBox box = collectionBoxRepository.findById(id).orElse(null);
        if (box != null) {
            box.getCurrencies().clear();
            box.setEmpty(true);
            box.setFundraisingEvent(null);
            collectionBoxRepository.save(box);
            collectionBoxRepository.deleteById(id);
        }
    }
    
    @Transactional
    public CollectionBox assignToEvent(Long boxId, Long eventId) {
        CollectionBox box = collectionBoxRepository.findById(boxId).orElse(null);
        FundraisingEvent event = fundraisingEventRepository.findById(eventId).orElse(null);
        
        if (box != null && event != null && box.isEmpty()) {
            box.setFundraisingEvent(event);
            return collectionBoxRepository.save(box);
        }
        return null;
    }
    
    @Transactional
    public CollectionBox addMoney(Long boxId, String currency, BigDecimal amount) {
        CollectionBox box = collectionBoxRepository.findById(boxId).orElse(null);
        if (box != null) {
            box.getCurrencies().merge(currency, amount, BigDecimal::add);
            box.setEmpty(false);
            return collectionBoxRepository.save(box);
        }
        return null;
    }
    
    @Transactional
    public FundraisingEvent emptyBox(Long boxId) {
        CollectionBox box = collectionBoxRepository.findById(boxId).orElse(null);
        if (box != null && box.getFundraisingEvent() != null && !box.isEmpty()) {
            FundraisingEvent event = box.getFundraisingEvent();
            
            for (Map.Entry<String, BigDecimal> entry : box.getCurrencies().entrySet()) {
                String currency = entry.getKey();
                BigDecimal amount = entry.getValue();
                
                BigDecimal convertedAmount = currencyExchangeService.convert(
                    amount, currency, event.getCurrency());
                
                event.setAmount(event.getAmount().add(convertedAmount));
            }
            
            box.getCurrencies().clear();
            box.setEmpty(true);
            collectionBoxRepository.save(box);
            return fundraisingEventRepository.save(event);
        }
        return null;
    }
}
