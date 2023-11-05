package com.nexttraverl.guide_service.repository;

import com.nexttraverl.guide_service.entity.Guide;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableMongoRepositories
public interface GuideRepository extends MongoRepository<Guide, String> {
    boolean existsById(String id);
    boolean deleteGuideById(String id);

    @Query(value = "{}", sort = "{id: -1}", fields = "{id: 1}")
    List<Guide> getLastGuideId();
    Guide getGuideById(String id);

}
