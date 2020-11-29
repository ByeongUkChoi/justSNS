package com.byeongukchoi.justSNS.api.auth;

import com.byeongukchoi.justSNS.user.dto.UserDto;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignInApiTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private static final String url = "/auth/signin";
    private static final String USERNAME = "test01";
    private static final String PASSWORD = "password01";

    @Test
    public void success() throws JSONException {
        UserDto.SignInRequest signInRequest = new UserDto.SignInRequest();
        ReflectionTestUtils.setField(signInRequest, "username", USERNAME);
        ReflectionTestUtils.setField(signInRequest, "password", PASSWORD);


        assertThat(true);
        HttpEntity<UserDto.SignInRequest> request = new HttpEntity<>(signInRequest);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        String jwt = response.getBody();

        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
    }
    @Test
    public void wrongUsername() {
        UserDto.SignInRequest signInRequest = new UserDto.SignInRequest();
        ReflectionTestUtils.setField(signInRequest, "username", "wrong-username");
        ReflectionTestUtils.setField(signInRequest, "password", PASSWORD);

        HttpEntity<UserDto.SignInRequest> request = new HttpEntity<>(signInRequest);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        assertThat(HttpStatus.UNAUTHORIZED).isEqualTo(response.getStatusCode());

        /**
         * restTemplate에서 401 응답 처리가 불가능 하여 HttpRetryException을 발생 시킴
         * 아래 dependency를 build.gradle에 추가하여 해결함
         * https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient
         * compile group: 'org.apache.httpcomponents', name: 'httpclient', version: '4.5.13'
         */
    }
}
