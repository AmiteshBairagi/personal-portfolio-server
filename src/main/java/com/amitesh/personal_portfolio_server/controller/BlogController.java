package com.amitesh.personal_portfolio_server.controller;

import com.amitesh.personal_portfolio_server.model.Blog;
import com.amitesh.personal_portfolio_server.service.BlogService;
import com.amitesh.personal_portfolio_server.service.CloudinaryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class BlogController {
    private final BlogService service;
    private final CloudinaryService cloudinaryService;

    public BlogController(BlogService service, CloudinaryService cloudinaryService) {
        this.service = service;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/get-all-blogs")
    public ResponseEntity<?> getAllBlogs(){
        return service.getAllBlogs();
    }

    @GetMapping("/get-blogs-by-category/{category}")
    public ResponseEntity<?> getBlogsByCategory(@PathVariable String category){
        return service.getBlogsByCategory(category);
    }

    @PostMapping(value = "/add-blog", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addBlog(@RequestPart("data") Blog blog,
                                     @RequestPart(value = "image", required = true) MultipartFile image){
        String imageUrl = cloudinaryService.upload(image);
        blog.setImageUrl(imageUrl);

        return service.addBlog(blog);
    }

    @DeleteMapping("/delete-blog/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable String id){
        return service.deleteBlog(id);
    }

    @GetMapping("/blog/{slug}")
    public ResponseEntity<?> fetchBlogBySlug(@PathVariable String slug){
        return service.fetchBlogBySlug(slug);
    }
}
