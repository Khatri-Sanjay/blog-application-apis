package com.sanjay.blog.services;

import com.sanjay.blog.Response.PostResponse;
import com.sanjay.blog.dto.PostDto;
import com.sanjay.blog.entity.Post;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Long categoryId, Long userId);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, Long postId);

    void deletePost(Long postId);

    List<PostDto> getPostByUser(Long userId);

    List<PostDto> getPostByCategory(Long categoryId);

    List<PostDto> searchPosts(String keyword);

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);


}
