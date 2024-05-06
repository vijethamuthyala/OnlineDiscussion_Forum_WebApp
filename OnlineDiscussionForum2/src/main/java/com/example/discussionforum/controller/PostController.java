package com.example.discussionforum.controller;

import com.example.discussionforum.model.Post;
import com.example.discussionforum.model.Thread;
import com.example.discussionforum.service.PostService;
import com.example.discussionforum.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final ThreadService threadService;

    @Autowired
    public PostController(PostService postService, ThreadService threadService) {
        this.postService = postService;
        this.threadService = threadService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping
    public @ResponseBody List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public @ResponseBody Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public @ResponseBody Post createPost(@RequestBody Post post) {
        post.setDate(new Date());
        return postService.createPost(post);
    }

    @PutMapping("/{id}")
    public @ResponseBody Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @GetMapping("/posts")
    public String getAllPosts(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "post-list";
    }

    @GetMapping("/posts/new")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "create-post";
    }

    @PostMapping("/posts/new")
    public String createNewPost(@ModelAttribute Post post) {
        post.setDate(new Date());
        postService.createPost(post);
        return "redirect:/api/posts/posts";
    }

    @GetMapping("/post/{id}")
    public String showPostDetail(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        if (post == null) {
            return "error";
        }
        model.addAttribute("post", post);
        model.addAttribute("thread", new Thread()); // Add an empty thread object
        return "post-detail";
    }

    @PostMapping("/posts/{postId}/threads/new")
    public String createThread(@PathVariable Long postId, @ModelAttribute Thread thread) {
        Post post = postService.getPostById(postId);
        if (post != null) {
            thread.setPost(post);
            threadService.createThread(thread);
        }
        return "redirect:/api/posts/post/{postId}";
    }
}
