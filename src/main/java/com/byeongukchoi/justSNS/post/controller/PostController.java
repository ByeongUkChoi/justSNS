package com.byeongukchoi.justSNS.post.controller;

import com.byeongukchoi.justSNS.common.dto.PagedResponse;
import com.byeongukchoi.justSNS.post.dto.PostDto;
import com.byeongukchoi.justSNS.security.CurrentUser;
import com.byeongukchoi.justSNS.security.UserPrincipal;
import com.byeongukchoi.justSNS.post.service.PostService;
import com.byeongukchoi.justSNS.common.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @GetMapping
    public PagedResponse getPosts(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
                                          @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int size) {
        return service.getPosts(pageNumber, size);
    }

//    원본
//    @GetMapping("/{postId}")
//    public PostDto.Response getPostById(@PathVariable long postId) {
//        PostDto.Response post = service.getPostById(postId);
//        return service.getPostById(postId);
//    }
    @GetMapping("/{postId}")
    public EntityModel<PostDto.Response> getPostById(@CurrentUser UserPrincipal userPrincipal, @PathVariable long postId) {
        PostDto.Response post = service.getPostById(postId);
        EntityModel<PostDto.Response> entityModel = EntityModel.of(post,
                linkTo(methodOn(PostController.class).getPostById(userPrincipal, postId)).withSelfRel(),
                linkTo(methodOn(PostController.class).getPosts(0, 20)).withRel("list"),
                //linkTo(methodOn(PostController.class).deletePost(userPrincipal, postId)).withRel("delete"),   // ERROR void type not allowed
                linkTo(methodOn(PostController.class).updatePost(userPrincipal, new PostDto.Update(), postId)).withRel("update"));
        return entityModel;
    }
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
    @DeleteMapping("/{postId}")
    public void deletePost(@CurrentUser UserPrincipal userPrincipal,
                             @PathVariable long postId) {
        service.deletePost(postId, userPrincipal);
    }
}
