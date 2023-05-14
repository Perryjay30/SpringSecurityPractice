package com.springsecuritypractice.repository;

import com.springsecuritypractice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessoriesRepository extends JpaRepository<User, Integer> {
}
