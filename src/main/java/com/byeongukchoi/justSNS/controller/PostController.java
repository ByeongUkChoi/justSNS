package com.byeongukchoi.justSNS.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    @GetMapping("/test")
    public String test() {
        SecurityContext context = SecurityContextHolder.getContext();
        return "test - success";
    }
}
