package com.byeongukchoi.justSNS.service;

import com.byeongukchoi.justSNS.dto.PagedResponse;
import com.byeongukchoi.justSNS.dto.PostDto;
import com.byeongukchoi.justSNS.model.Post;
import com.byeongukchoi.justSNS.model.User;
import com.byeongukchoi.justSNS.repository.PostRepository;
import com.byeongukchoi.justSNS.repository.UserRepository;
import com.byeongukchoi.justSNS.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostDto.Response createPost(PostDto.Create postCreateDto, UserPrincipal userPrincipal) {

        // get current user
        Optional<User> optionalUser = userRepository.findById(userPrincipal.getId());
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException();
        }

        // create post entity and save
        Post post = postRepository.save(Post.builder()
                .subject(postCreateDto.getSubject())
                .content(postCreateDto.getContent())
                .user(optionalUser.get())
                .build());

        return new PostDto.Response(post);
    }

    public PagedResponse getPosts(int pageNumber, int size) {
        // Retrieve posts
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.Direction.DESC, "createdAt");
        Page<Post> posts = postRepository.findAll(pageable);
        long totalElements = postRepository.count();

        // Page<Entity> -> List<Dto>
        List<PostDto.Response> postResponseDtoList = posts.map(post -> new PostDto.Response(post)).getContent();

        return new PagedResponse<>(postResponseDtoList, pageNumber, size, totalElements);
    }

    public PostDto.Response getPostById(long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if(!optionalPost.isPresent()) {
            throw new ResourceNotFoundException();
        }
        return new PostDto.Response(optionalPost.get());
    }

    public PostDto.Response updatePost(PostDto.Update postUpdateDto, long postId, UserPrincipal userPrincipal) {

        // get user entity
        Optional<User> optionalUser = userRepository.findById(userPrincipal.getId());
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException();
        }
        User user = optionalUser.get();

        // get post entity
        Optional<Post> optionalPost = postRepository.findById(postId);
        if(!optionalPost.isPresent()) {
            throw new ResourceNotFoundException();
        }
        Post post = optionalPost.get();

        // valid author
        verifyAuthor(post, user);

        // update entity
        post.update(postUpdateDto);
        // persist
        Post updatedPost = postRepository.save(post);

        return new PostDto.Response(post);
    }

    public void deletePost(long postId, UserPrincipal userPrincipal) {
        // get user entity
        Optional<User> optionalUser = userRepository.findById(userPrincipal.getId());
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException();
        }
        User user = optionalUser.get();

        // get post entity
        Optional<Post> optionalPost = postRepository.findById(postId);
        if(!optionalPost.isPresent()) {
            throw new ResourceNotFoundException();
        }
        Post post = optionalPost.get();

        // valid author
        verifyAuthor(post, user);

        postRepository.delete(post);
    }

    // 현재 사용자와 게시판의 작성자 확인
    private void verifyAuthor(Post post, User currentUser) {
        if(post.getAuthorId() != currentUser.getId()) {
            // TODO: 예외처리
            throw new AuthorizationServiceException("Error author id");
        }
    }
}
