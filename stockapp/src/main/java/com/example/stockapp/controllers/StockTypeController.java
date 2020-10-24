package com.example.stockapp.controllers;

import java.util.List;

import com.example.stockapp.exceptions.ResourceNotFoundException;
import com.example.stockapp.models.StockType;
import com.example.stockapp.repositories.StockTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StockTypeController {
    
    @Autowired
    private StockTypeRepository stockTypeRepository;

    @GetMapping("/stocktypes")
    public List<StockType> getAllStockItemTypes() {
        return stockTypeRepository.findAll();
    }

    @GetMapping("/stocktypes/{id}")
    public ResponseEntity<StockType> getStockTypeById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
                StockType stockType = stockTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StockType not found for this id :: " + id));
        return ResponseEntity.ok().body(stockType);
    }

    /*
    @PostMapping("/departments")
    public Department createDepartment(@Validated @RequestBody Department department) {
        return departmentRepository.save(department);
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable(value = "id") Long departmentId,
            @Validated @RequestBody Department departmentDetails) throws ResourceNotFoundException {

        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department not found for this id :: " + departmentId));

        department.setName(departmentDetails.getName());
        final Department updatedDepartment = departmentRepository.save(department);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/departments/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long departmentId)
            throws ResourceNotFoundException {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department not found for this id :: " + departmentId));

        departmentRepository.delete(department);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    */
}