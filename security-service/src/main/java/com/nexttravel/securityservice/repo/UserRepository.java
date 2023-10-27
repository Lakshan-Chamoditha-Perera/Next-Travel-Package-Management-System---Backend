package com.nexttravel.securityservice.repo;

import com.nexttravel.securityservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String name);

    Boolean existsByUsername(String name);

    Boolean existsByEmail(String email);
}
