package com.example.stockapp.repositories;

import com.example.stockapp.models.Centre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CentreRepository extends JpaRepository<Centre, Long> {
    
    public Centre findByName(String name);
    
}