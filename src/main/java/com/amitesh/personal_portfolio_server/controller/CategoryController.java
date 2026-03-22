package com.amitesh.personal_portfolio_server.controller;

import com.amitesh.personal_portfolio_server.model.Category;
import com.amitesh.personal_portfolio_server.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add-category")
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @DeleteMapping("/delete-category/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable String categoryId){
        return categoryService.deleteCategory(categoryId);
    }

    @GetMapping("/get-all-categories")
    public ResponseEntity<?> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PutMapping("/update-category")
    public ResponseEntity<?> updateCategory(@RequestBody Category category){
        return categoryService.updateCategory(category);
    }
}
