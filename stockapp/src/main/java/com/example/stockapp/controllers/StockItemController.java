package com.example.stockapp.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.example.stockapp.exceptions.ResourceNotFoundException;
import com.example.stockapp.models.StockItem;
import com.example.stockapp.models.StockItemType;
import com.example.stockapp.repositories.StockItemRepository;
import com.example.stockapp.utils.StockExcelImport;
import com.example.stockapp.utils.StockItemUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StockItemController {
    
    @Autowired
    private StockItemRepository stockItemRepository;

    @Autowired
    private StockExcelImport stockExcelImport;

    @GetMapping("/stockItems")
    public List<StockItem> getAllStockItems() {
        return stockItemRepository.findAll();
    }

    @GetMapping("/stockItems/{id}")
    public ResponseEntity<StockItem> getStockItemById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
                StockItem stockItem = stockItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StockItem not found for this id :: " + id));
        return ResponseEntity.ok().body(stockItem);
    }

    @GetMapping("/stockItems/import")
    public ResponseEntity<StockItem> importStockItems() throws ResourceNotFoundException, IOException {
        //File file = new File("\\finalData");
        System.out.println();
        System.out.println();

        File file = new ClassPathResource("finalData").getFile();
        System.out.println(file.getAbsolutePath());
		
		// TODO: CAUTION-This will delete all stock items from database before starting import
		StockItemRepository stockRepo = stockExcelImport.getStockItemRepository();
		if(stockRepo != null) {
			stockRepo.deleteAll();
		}
		
		if(file != null && file.exists() && file.isDirectory()) {
			File[] importFiles = file.listFiles();
			
			if(importFiles != null && importFiles.length > 0) {
				for(File importFile : importFiles) {
                    System.out.println(importFile.getAbsolutePath());
                    System.out.println();
                    
					if (importFile != null && importFile.exists() && importFile.isFile()) {
						if(importFile.getName().contains("Stock as on today")) {
							StockItemType regular = stockExcelImport.getStockItemTypeRepository().findByName(StockItemUtil.STOCK_ITEM_TYPE_REGULAR);
							stockExcelImport.importStockFromExcel(importFile, regular);
						} else {
							StockItemType loan = stockExcelImport.getStockItemTypeRepository().findByName(StockItemUtil.STOCK_ITEM_TYPE_LOAN);
							stockExcelImport.importStockFromExcel(importFile, loan);
						}
					} else {
						System.out.println("Problems while reading file: " + importFile.getName());
					}
				}
			}
        }
        return getStockItemById(Long.valueOf("1"));
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