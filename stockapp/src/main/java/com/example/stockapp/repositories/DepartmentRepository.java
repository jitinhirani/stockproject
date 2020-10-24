package com.example.stockapp.repositories;

import com.example.stockapp.models.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
    public Department findByName(String name);

}