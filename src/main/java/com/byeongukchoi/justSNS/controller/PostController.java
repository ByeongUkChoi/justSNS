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
        PostDto.Response post = service.getPostById(postId);
        return service.getPostById(postId);
    }
//    @GetMapping("/{postId}")
//    public EntityModel<PostDto.Response> getPostById(@PathVariable long postId) {
//        PostDto.Response post = service.getPostById(postId);
//        EntityModel<PostDto.Response> employees = EntityModel.of(post,
//                linkTo(methodOn(PostController.class).getPostById(postId)).withSelfRel());
//        return employees;
//    }
//    @GetMapping("/{postId}")
//    public PostDto.ResponseModel getPostById(@PathVariable long postId) {
//        PostDto.Response response = service.getPostById(postId);
//        PostDto.ResponseModel responseModel = new PostDto.ResponseModel(response);
//        Link abc = Link.of("abc");
//        responseModel.add(abc);
//
//        return responseModel;
//    }
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
