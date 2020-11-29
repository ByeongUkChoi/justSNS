package com.byeongukchoi.justSNS.api.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class getPostsTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private static final String url = "/posts";

    @Test
    @WithMockUser(username = "test0101")
    public void success() {

        final HttpHeaders headers = new HttpHeaders();
        // TODO: 공통 함수 혹은 intercepter사용. 토큰은 환경 변수로 분리
        headers.set("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDAwMSIsImlhdCI6MTYwNTE1NTM3MSwiZXhwIjoxNzA2NTE3NTM3fQ.uqbJyMqGD7JEoPt9z2zXOV5QOt6FYmn2CqrvTCRHEqh27wrm36ZnjlT9gBgYP5dPWu-HglK6U40xDst2KBCasA");

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());

        //JSONObject actual = new JSONObject(response.getBody());
    }
}
