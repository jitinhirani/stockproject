package com.example.stockapp.repositories;

import com.example.stockapp.models.Source;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SourceRepository extends JpaRepository<Source, Long> {

    public Source findByName(String name);
    
}