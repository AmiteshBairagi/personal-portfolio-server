package com.amitesh.personal_portfolio_server.service;

import com.amitesh.personal_portfolio_server.dto.BlogUpdateRequest;
import com.amitesh.personal_portfolio_server.model.Blog;
import com.amitesh.personal_portfolio_server.repo.BlogRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BlogService {
    private final BlogRepo repo;
    private final CloudinaryService cloudinaryService;


    public BlogService(BlogRepo repo, CloudinaryService cloudinaryService) {
        this.repo = repo;

        this.cloudinaryService = cloudinaryService;
    }

    @Transactional
    public ResponseEntity<?> addBlog(Blog blog) {
        try{
            System.out.println("In service");
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

    public ResponseEntity<?> getBlogById(String id) {
        try{
            Blog blog = repo.findById(id).orElseThrow(() -> new RuntimeException("Blog not found with the id"));
            return ResponseEntity.status(200).body(blog);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Something went wrong");
        }
    }

    public ResponseEntity<?> updateBlog(BlogUpdateRequest blogUpdateRequest, MultipartFile image, String id) {
        try{
            Blog blog = repo.findById(id).orElseThrow(() -> new RuntimeException());
            if(image != null && !image.isEmpty()){
                Map cloudinaryResponse = cloudinaryService.update(image,blog.getImagePublicId());
                String imagePublicId = cloudinaryResponse.get("public_id").toString();
                String imageUrl = cloudinaryResponse.get("secure_url").toString();

                blog.setImagePublicId(imagePublicId);
                blog.setImageUrl(imageUrl);
            }

            if(blogUpdateRequest.getTitle() != null){
                blog.setTitle(blogUpdateRequest.getTitle());
            }
            if (blogUpdateRequest.getSlug() != null)
                blog.setSlug(blogUpdateRequest.getSlug());

            if (blogUpdateRequest.getExcerpt() != null)
                blog.setExcerpt(blogUpdateRequest.getExcerpt());

            if (blogUpdateRequest.getContent() != null)
                blog.setContent(blogUpdateRequest.getContent());

            if (blogUpdateRequest.getAuthor() != null)
                blog.setAuthor(blogUpdateRequest.getAuthor());

            if (blogUpdateRequest.getTags() != null)
                blog.setTags(blogUpdateRequest.getTags());

            if (blogUpdateRequest.getCategory() != null)
                blog.setCategory(blogUpdateRequest.getCategory());

            if (blogUpdateRequest.getFeatured() != null)
                blog.setFeatured(blogUpdateRequest.getFeatured());

            if (blogUpdateRequest.getPublished() != null)
                blog.setPublished(blogUpdateRequest.getPublished());


            repo.save(blog);

            return ResponseEntity.status(200).body("Blog Updated Successfully");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Blog cannot be updated");
        }
    }
}
