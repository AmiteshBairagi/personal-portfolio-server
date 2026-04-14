package com.amitesh.personal_portfolio_server.service;

import com.amitesh.personal_portfolio_server.model.Blog;
import com.amitesh.personal_portfolio_server.repo.BlogRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {
    private final BlogRepo repo;


    public BlogService(BlogRepo repo) {
        this.repo = repo;

    }

    @Transactional
    public ResponseEntity<?> addBlog(Blog blog) {
        try{
            blog.setCreatedAt(LocalDateTime.now());
            blog.setUpdatedAt(LocalDateTime.now());
            blog.setPublishedAt(LocalDateTime.now());
            repo.save(blog);
            return ResponseEntity.status(200).body("Data saved successfully");
        }catch(Exception e){
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
    @Transactional
    public ResponseEntity<?> getAllBlogs() {
        try{
            List<Blog> result = new ArrayList<>();
            result = repo.findAll();
            return ResponseEntity.status(200).body(result);
        }catch(Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }
    @Transactional
    public ResponseEntity<?> deleteBlog(String id) {
        try{
            repo.deleteById(id);
            return ResponseEntity.status(200).body("Deleted");
        }catch(Exception e){
            return ResponseEntity.status(500).body("Cannot be deleted . something went wromg");
        }
    }

    public ResponseEntity<?> getBlogsByCategory(String category) {
        try{
            List<Blog> result = new ArrayList<>();
            result = repo.findByCategoryIgnoreCase(category);
            return ResponseEntity.status(200).body(result);
        }catch(Exception e){
            return ResponseEntity.status(500).body("Server error");
        }
    }

    public ResponseEntity<?> fetchBlogBySlug(String slug) {
        try{
            Blog blog = repo.findBySlug(slug);
            return ResponseEntity.status(200).body(blog);
        }catch (Exception e){
            return ResponseEntity.status(500).body("Cannot find blog with the given slug , something went wrong");
        }
    }
}
