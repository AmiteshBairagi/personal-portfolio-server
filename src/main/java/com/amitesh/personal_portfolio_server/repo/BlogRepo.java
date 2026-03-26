package com.amitesh.personal_portfolio_server.repo;

import com.amitesh.personal_portfolio_server.model.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepo extends MongoRepository<Blog,String> {
    List<Blog> findByCategoryIgnoreCase(String category);

    Blog findBySlug(String slug);
}
