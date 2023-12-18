package com.sanjay.blog.repository;

import com.sanjay.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
