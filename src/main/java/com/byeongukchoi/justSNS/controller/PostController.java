package com.byeongukchoi.justSNS.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    @GetMapping
    public String getPosts() {
        //SecurityContext context = SecurityContextHolder.getContext();
        return "test - getPosts";
    }
    @GetMapping("/{postId}")
    public String getPostById(@PathVariable long postId) {
        return "test - getPostById : " + postId;
    }
    @PostMapping
    public String createPost() {
        return "test - createPost";
    }
    @PatchMapping
    public String updatePost(@PathVariable long postId) {
        return "test - updatePost : " + postId;
    }
    @DeleteMapping
    public String deletePost(@PathVariable long postId) {
        return "test - deletePost : " + postId;
    }
}
