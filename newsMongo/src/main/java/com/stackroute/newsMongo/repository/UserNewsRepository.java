package com.stackroute.newsMongo.repository;

import com.stackroute.newsMongo.model.UserNews;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNewsRepository extends MongoRepository<UserNews, String> {
}
