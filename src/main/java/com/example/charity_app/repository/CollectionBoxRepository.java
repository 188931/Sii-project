package com.example.charity_app.repository;

import com.example.charity_app.domain.CollectionBox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionBoxRepository extends JpaRepository<CollectionBox, Long> {
    List<CollectionBox> findAll();
    CollectionBox findByIdentifier(String identifier);
}
