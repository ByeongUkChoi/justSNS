package com.byeongukchoi.justSNS.post.service;

import com.byeongukchoi.justSNS.common.dto.PagedResponse;
import com.byeongukchoi.justSNS.post.dto.PostDto;
import com.byeongukchoi.justSNS.post.domain.Post;
import com.byeongukchoi.justSNS.user.domain.User;
import com.byeongukchoi.justSNS.post.repository.PostRepository;
import com.byeongukchoi.justSNS.user.repository.UserRepository;
import com.byeongukchoi.justSNS.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostDto.Response createPost(PostDto.Create postCreateDto, UserPrincipal userPrincipal) {

        // get user entity
        User user = userRepository.findById(userPrincipal.getId())
                //.orElseThrow(() -> new ResourceNotFoundException());  // 람다식에서 생성자 레퍼런스로 아래 처럼 사용 가능
                //.orElseThrow(ResourceNotFoundException::new);   // Supplier 타입 반환 됨. 생성자 레퍼런스를 사용할 경우 lazy initialize 됨.
                .orElseThrow(RuntimeException::new);   // Supplier 타입 반환 됨. 생성자 레퍼런스를 사용할 경우 lazy initialize 됨.

        // create post entity and save
        Post post = postRepository.save(Post.builder()
                .subject(postCreateDto.getSubject())
                .content(postCreateDto.getContent())
                .authorId(user.getId())
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
        Post post = postRepository.findById(postId)
                //.orElseThrow(ResourceNotFoundException::new);
                .orElseThrow(RuntimeException::new);
        return new PostDto.Response(post);
    }

    public PostDto.Response updatePost(PostDto.Update postUpdateDto, long postId, UserPrincipal userPrincipal) {

        // get user entity
        User user = userRepository.findById(userPrincipal.getId())
                //.orElseThrow(ResourceNotFoundException::new);
                .orElseThrow(RuntimeException::new);

        // get post entity
        Post post = postRepository.findById(postId)
                //.orElseThrow(ResourceNotFoundException::new);
                .orElseThrow(RuntimeException::new);

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
        User user = userRepository.findById(userPrincipal.getId())
                //.orElseThrow(ResourceNotFoundException::new);
                .orElseThrow(RuntimeException::new);

        // get post entity
        Post post = postRepository.findById(postId)
                //.orElseThrow(ResourceNotFoundException::new);
                .orElseThrow(RuntimeException::new);

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
