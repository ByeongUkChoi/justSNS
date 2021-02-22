package com.byeongukchoi.justSNS.api.post.api;

import com.byeongukchoi.justSNS.common.dto.PagedResponse;
import com.byeongukchoi.justSNS.post.dto.PostDto;
import com.byeongukchoi.justSNS.post.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.util.ReflectionTestUtils.setField;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@WithMockUser
public class PostApiTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    public void getPosts() throws Exception {
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

        // then
        result.andExpect(status().isOk())
                .andDo(document("get-posts",
                        responseFields(
                                fieldWithPath("item").type(JsonFieldType.ARRAY).description("posts"),
                                // TODO: item.
//                                fieldWithPath("item.xxx").type(JsonFieldType.ARRAY).description("posts"),
                                fieldWithPath("page").type(JsonFieldType.OBJECT).description("page"),
                                fieldWithPath("page.number").type(JsonFieldType.NUMBER).description("page number"),
                                fieldWithPath("page.size").type(JsonFieldType.NUMBER).description("page size"),
                                fieldWithPath("page.totalElements").type(JsonFieldType.NUMBER).description("page total count")
                        )
                ));
    }
    @Test
    public void addPost() throws Exception {
        // given
        PostDto.Create postCreateDto = mock(PostDto.Create.class);
        setField(postCreateDto, "content", "This is content");
        // TODO: dto -> json string
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(postCreateDto);
        // when
        ResultActions result = mockMvc.perform(
                post("/posts")
                        .content(objectMapper.writeValueAsString(postCreateDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );
        // then TODO:
        result.andExpect(status().isCreated());
    }
}
