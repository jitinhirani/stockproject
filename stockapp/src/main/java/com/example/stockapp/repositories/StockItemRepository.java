package com.example.stockapp.repositories;

import com.example.stockapp.models.StockItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StockItemRepository extends JpaRepository<StockItem, Long> {

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value="ALTER TABLE STOCKITEM ALTER COLUMN ID RESTART WITH 1;", nativeQuery = true)
    public void resetSequence();
    
}