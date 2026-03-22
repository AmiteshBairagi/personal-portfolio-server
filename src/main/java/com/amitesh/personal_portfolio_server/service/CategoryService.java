package com.amitesh.personal_portfolio_server.service;

import com.amitesh.personal_portfolio_server.model.Category;
import com.amitesh.personal_portfolio_server.repo.CategoryRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Transactional
    public ResponseEntity<?> addCategory(Category category) {
        try{
            category.setCreatedAt(LocalDateTime.now());
            category.setUpdatedAt(LocalDateTime.now());
            categoryRepo.save(category);
            return ResponseEntity.status(200).body("Category Saved Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Category cannot be saved, something went wrong");
        }
    }

    @Transactional
    public ResponseEntity<?> deleteCategory(String categoryId) {
        try{
            categoryRepo.deleteById(categoryId);
            return ResponseEntity.status(200).body("Category deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Category cannot be deleted , something went wrong");
        }
    }

    public ResponseEntity<?> getAllCategories() {
        try{
            List<Category> categories = new ArrayList<>();
            categories = categoryRepo.findAll();
            return ResponseEntity.status(200).body(categories);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Cannot fetch categories, something went wrong");
        }
    }

    @Transactional
    public ResponseEntity<?> updateCategory(Category category) {
        try{
            Category categoryInDb = categoryRepo.findById(category.getId()).orElseThrow(() -> new RuntimeException("Category not found"));
            categoryInDb.setName(category.getName());
            categoryInDb.setColor(category.getColor());
            categoryInDb.setIcon(category.getIcon());
            categoryInDb.setDescription(category.getDescription());
            categoryInDb.setUpdatedAt(LocalDateTime.now());
            categoryRepo.save(categoryInDb);
            return ResponseEntity.status(200).body("Category updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Something went wrong , category cannot be updated");
        }
    }
}
