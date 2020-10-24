package com.example.stockapp.repositories;

import com.example.stockapp.models.StockType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StockTypeRepository extends JpaRepository<StockType, Long> {
    
    public StockType findByName(String name);
    
}