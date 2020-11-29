package com.byeongukchoi.justSNS.api.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class getPostWithWebTestClientTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void success() {
        // TODO:
        webTestClient.get()
                .uri("/posts")
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDAwMSIsImlhdCI6MTYwNTE1NTM3MSwiZXhwIjoxNzA2NTE3NTM3fQ.uqbJyMqGD7JEoPt9z2zXOV5QOt6FYmn2CqrvTCRHEqh27wrm36ZnjlT9gBgYP5dPWu-HglK6U40xDst2KBCasA")
                .exchange()
                .expectStatus().isOk();
    }
}
