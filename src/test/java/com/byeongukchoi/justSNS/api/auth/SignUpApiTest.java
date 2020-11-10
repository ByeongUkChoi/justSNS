package com.byeongukchoi.justSNS.api.auth;

import com.byeongukchoi.justSNS.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignUpApiTest {
    @Autowired
    private TestRestTemplate restTemplate;

    static final String url = "/auth/signup";
    static final String USERNAME = "test01";
    static final String PASSWORD = "password01";
    static final String NAME = "Byeonguk Choi";
    static final String EMAIL = "test@test.com";

    @Test
    public void success() throws JSONException {

        UserDto.SignUpRequest signUpRequest = new UserDto.SignUpRequest();
        ReflectionTestUtils.setField(signUpRequest, "username", USERNAME);
        ReflectionTestUtils.setField(signUpRequest, "password", PASSWORD);
        ReflectionTestUtils.setField(signUpRequest, "name", NAME);
        ReflectionTestUtils.setField(signUpRequest, "email", EMAIL);

        HttpEntity<UserDto.SignUpRequest> request = new HttpEntity<>(signUpRequest);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        JSONObject actual = new JSONObject(response.getBody());
        JSONObject expected = new JSONObject();
        expected.put("username", USERNAME);
        expected.put("name", NAME);
        expected.put("email", EMAIL);

        //JSONAssert.assertEquals(expected, actual, JSONCompareMode.LENIENT);
        assertThat(HttpStatus.CREATED).isEqualTo(response.getStatusCode());
    }

    @Test
    public void missingRequiredParams() {
        final String url = "/auth/signup";
        UserDto.SignUpRequest signUpRequest = new UserDto.SignUpRequest();
        ReflectionTestUtils.setField(signUpRequest, "username", USERNAME);
        ReflectionTestUtils.setField(signUpRequest, "password", PASSWORD);
        ReflectionTestUtils.setField(signUpRequest, "name", NAME);
        //ReflectionTestUtils.setField(signUpRequest, "email", EMAIL);
        HttpEntity<UserDto.SignUpRequest> request = new HttpEntity<>(signUpRequest);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        assertThat(HttpStatus.BAD_REQUEST).isEqualTo(response.getStatusCode());
    }
}
