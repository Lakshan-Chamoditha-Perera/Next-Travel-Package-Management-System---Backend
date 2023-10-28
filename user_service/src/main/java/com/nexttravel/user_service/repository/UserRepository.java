package com.nexttravel.user_service.repository;

import com.nexttravel.user_service.entity.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableMongoRepositories
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findUserByUsername(String name);

    Boolean existsUserByUsername(String username);


    Boolean existsUserByEmail(String email);

    @Query(value = "{}", sort = "{user_id : -1}", fields = "{user_id : 1}")
    List<User> findLastInsertedUser();

    Boolean deleteUserByUsername(String username);
//    List<User> getAll();

    User getUserByUsername(String username);
}
