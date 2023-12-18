package com.sanjay.blog.services.impl;

import com.sanjay.blog.dto.PostDto;
import com.sanjay.blog.entity.Category;
import com.sanjay.blog.entity.Post;
import com.sanjay.blog.entity.User;
import com.sanjay.blog.exceptions.ResourceNotFoundException;
import com.sanjay.blog.repository.CategoryRepository;
import com.sanjay.blog.repository.PostRepository;
import com.sanjay.blog.repository.UserRepository;
import com.sanjay.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Long categoryId, Long userId) {

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost =  this.postRepository.save(post);

        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = this.postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", id));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatePost = this.postRepository.save(post);

        return this.modelMapper.map(updatePost, PostDto.class);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        this.postRepository.delete(post);
    }

    @Override
    public List<PostDto> getPostByUser(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

        List<Post> posts = this.postRepository.findByUser(user);

        List<PostDto> postDtos;
        postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostByCategory(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId));
        List<Post> posts = this.postRepository.findByCategory(category);

        List<PostDto> postDtos;
        postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = this.postRepository.findAll();
        List<PostDto> postDtos;
        postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
