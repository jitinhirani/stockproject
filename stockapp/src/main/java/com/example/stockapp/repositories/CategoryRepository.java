package com.example.stockapp.repositories;

import com.example.stockapp.models.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    public Category findByName(String name);

}