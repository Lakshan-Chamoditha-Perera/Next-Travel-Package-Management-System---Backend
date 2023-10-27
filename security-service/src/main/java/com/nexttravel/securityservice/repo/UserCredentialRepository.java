package com.nexttravel.securityservice.repo;

import com.nexttravel.securityservice.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UserCredentialRepository extends JpaRepository<UserCredential,Integer> {
}
