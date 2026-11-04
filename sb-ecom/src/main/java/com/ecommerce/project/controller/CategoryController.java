package com.ecommerce.project.controller;

import com.ecommerce.project.model.category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.project.service.CategoryService;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @GetMapping("/api/public/categories")
    public ResponseEntity<List<category>> getAllCategories() {

        List<category> Categories = categoryService.getAllCategories();
        return new ResponseEntity<>(Categories,HttpStatus.OK);
    }

    @PostMapping("/api/public/categories")
    public ResponseEntity<String> createCategory(@RequestBody category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category added successfully",HttpStatus.CREATED);
    }
    @DeleteMapping("/api/admit/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        try {
            String status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(
                    e.getReason(),
                    (HttpStatus) e.getStatusCode()
            );
        }
    }
    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(
            @RequestBody category category,
            @PathVariable Long categoryId) {

        try {

            category.setCategoryId(categoryId);

            category savedCategory = categoryService.updateCategory(category);

            return new ResponseEntity<>(
                    "Category updated successfully",
                    HttpStatus.OK
            );

        } catch (ResponseStatusException e) {

            return new ResponseEntity<>(
                    e.getReason(),
                    (HttpStatus) e.getStatusCode()
            );
        }
    }

}