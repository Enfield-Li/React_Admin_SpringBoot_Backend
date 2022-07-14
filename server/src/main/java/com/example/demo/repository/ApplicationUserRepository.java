package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ApplicationUser;

@Repository
public interface ApplicationUserRepository
  extends JpaRepository<ApplicationUser, Long> {
  Optional<ApplicationUser> findByUsername(String username);
}
