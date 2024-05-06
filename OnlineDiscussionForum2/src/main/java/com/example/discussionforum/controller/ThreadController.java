package com.example.discussionforum.controller;

import com.example.discussionforum.model.Post;
import com.example.discussionforum.model.Thread;
import com.example.discussionforum.service.PostService;
import com.example.discussionforum.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ThreadController {

    private final PostService postService;
    private final ThreadService threadService;

    @Autowired
    public ThreadController(PostService postService, ThreadService threadService) {
        this.postService = postService;
        this.threadService = threadService;
    }

    @PostMapping("/posts/{postId}/threads/new")
    public ResponseEntity<String> createThread(@PathVariable Long postId, @ModelAttribute Thread thread) {
        System.out.println("Received Thread object:");
        System.out.println(thread.toString()); // Print out the received Thread object

        Post post = postService.getPostById(postId);
        if (post != null) {
            thread.setPost(post);
            threadService.createThread(thread);
            // Redirect to the post detail page after creating the thread
            return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, "/api/posts/" + postId).body(null);
        } else {
            // Return 404 Not Found if the post is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
        }
    }

    @GetMapping("/posts/{postId}/threads")
    public ResponseEntity<List<Thread>> getAllThreadsByPostId(@PathVariable Long postId) {
        System.out.println("Fetching threads for post with ID: " + postId);

        Post post = postService.getPostById(postId);
        if (post != null) {
            List<Thread> threads = threadService.getAllThreadsByPost(post);
            return ResponseEntity.ok(threads);
        } else {
            // Return 404 Not Found if the post is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
