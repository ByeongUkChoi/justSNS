package com.byeongukchoi.justSNS.api.auth;

import com.byeongukchoi.justSNS.dto.UserDto;
import org.json.JSONException;
import org.json.JSONObject;
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

    static final String url = "/auth/signin";
    static final String USERNAME = "test01";
    static final String PASSWORD = "password01";

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
}
