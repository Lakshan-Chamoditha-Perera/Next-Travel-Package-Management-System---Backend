package com.next_travel.user.repository;

import com.next_travel.user.entity.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,String> {
    Optional<User> findUserByUsername(String name);
    Boolean existsUserByUsername(String username);
    Boolean existsByEmail(String email);

    @Query(value = "{}", sort = "{ 'user_id' : -1 }", fields = "{ 'user_id' : 1 }")
    User findLastUserId();
}
