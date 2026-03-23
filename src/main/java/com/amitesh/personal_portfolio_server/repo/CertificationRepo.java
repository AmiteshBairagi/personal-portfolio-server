package com.amitesh.personal_portfolio_server.repo;

import com.amitesh.personal_portfolio_server.model.Certification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepo extends MongoRepository<Certification,String> {
}
