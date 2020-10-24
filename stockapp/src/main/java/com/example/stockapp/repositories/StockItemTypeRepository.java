package com.example.stockapp.repositories;

import com.example.stockapp.models.StockItemType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StockItemTypeRepository extends JpaRepository<StockItemType, Long> {
    
    public StockItemType findByName(String name);

}