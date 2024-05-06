package com.example.discussionforum.service;

import com.example.discussionforum.model.Post;
import com.example.discussionforum.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        return postOptional.orElse(null);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post newPost) {
        newPost.setId(id);  // Set the ID of the new post to match the existing post
        return postRepository.save(newPost);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
