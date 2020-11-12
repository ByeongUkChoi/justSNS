package com.byeongukchoi.justSNS.controller;

import com.byeongukchoi.justSNS.dto.PagedResponse;
import com.byeongukchoi.justSNS.dto.PostDto;
import com.byeongukchoi.justSNS.security.CurrentUser;
import com.byeongukchoi.justSNS.security.UserPrincipal;
import com.byeongukchoi.justSNS.service.PostService;
import com.byeongukchoi.justSNS.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @GetMapping
    public PagedResponse getPosts(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNumber,
                                          @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {

        return service.getPosts(pageNumber, size);
    }
    @GetMapping("/{postId}")
    public PostDto.Response getPostById(@PathVariable long postId) {
        return service.getPostById(postId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto.Response createPost(@CurrentUser UserPrincipal userPrincipal,
                                       @Valid @RequestBody PostDto.Create postCreateDto) {
        return service.createPost(postCreateDto, userPrincipal);
    }
    @PatchMapping("/{postId}")
    public PostDto.Response updatePost(@CurrentUser UserPrincipal userPrincipal,
                                       @Valid @RequestBody PostDto.Update postUpdateDto,
                                       @PathVariable long postId) {
        return service.updatePost(postUpdateDto, postId, userPrincipal);
    }
    @DeleteMapping
    public void deletePost(@CurrentUser UserPrincipal userPrincipal,
                             @PathVariable long postId) {
        service.deletePost(postId, userPrincipal);
    }
}
