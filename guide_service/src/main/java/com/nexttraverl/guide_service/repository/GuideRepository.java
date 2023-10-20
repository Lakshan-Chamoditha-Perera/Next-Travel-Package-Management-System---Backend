package com.nexttraverl.guide_service.repository;

import com.nexttraverl.guide_service.entity.Guide;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories
public interface GuideRepository extends MongoRepository<Guide, String> {
}
