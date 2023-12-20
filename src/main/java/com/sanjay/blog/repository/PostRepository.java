package com.sanjay.blog.repository;

import com.sanjay.blog.entity.Category;
import com.sanjay.blog.entity.Post;
import com.sanjay.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);

}
