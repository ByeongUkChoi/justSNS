package com.byeongukchoi.justSNS.api.post.api;

import com.byeongukchoi.justSNS.common.dto.PagedResponse;
import com.byeongukchoi.justSNS.post.dto.PostDto;
import com.byeongukchoi.justSNS.post.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.util.ReflectionTestUtils.setField;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class PostApiTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    public void getPost() throws Exception {
        // given
        PostDto.Response postResponseDto = mock(PostDto.Response.class);
        setField(postResponseDto, "id", 1L);
        setField(postResponseDto, "content", "This is content");
        setField(postResponseDto, "createdAuthorId", 1L);
        setField(postResponseDto, "createdAt", ZonedDateTime.now());
        setField(postResponseDto, "updatedAt", ZonedDateTime.now());

        List<PostDto.Response> list = new ArrayList<>();
        PagedResponse<PostDto.Response> pagedResponse = new PagedResponse<>(list, 0, 20, list.size());
        given(postService.getPosts(anyInt(), anyInt()))
                .willReturn(pagedResponse);

        // TODO: when. 내용 추가
        ResultActions result = mockMvc.perform(
                get("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        // TODO: then. 도큐멘트 추가
//        result.andExpect(status().isOk()).andDo(document());
    }
}
