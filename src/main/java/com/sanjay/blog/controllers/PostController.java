package com.sanjay.blog.controllers;

import com.sanjay.blog.Response.ApiResponse;
import com.sanjay.blog.dto.PostDto;
import com.sanjay.blog.entity.Post;
import com.sanjay.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Long userId,
                                              @PathVariable Long categoryId
    ) {
        PostDto createPost = this.postService.createPost(postDto, categoryId, userId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategories(
            @PathVariable Long categoryId
    ) {
        List<PostDto> postDtos = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(
            @PathVariable Long userId
    ) {
        List<PostDto> postDtos = this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> postDtos = this.postService.getAllPosts();
        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {
        PostDto postDto = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto, HttpStatus.FOUND);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Long postId) {
        PostDto updatePost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId) {
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted Successfully", true), HttpStatus.OK);
    }
}
