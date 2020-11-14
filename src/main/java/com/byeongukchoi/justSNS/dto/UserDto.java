package com.byeongukchoi.justSNS.dto;

import com.byeongukchoi.justSNS.model.User;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

public class UserDto {
    @Getter
    public static class SignUpRequest {
        @NotBlank
        @Size(min = 3, max = 15)
        private String username;
        @NotBlank
        @Size(min = 8, max = 20)
        private String password;
        @NotBlank
        @Size(min = 3, max = 20)
        private String name;
        @NotBlank
        @Email
        @Size(max = 40)
        private String email;
    }

    @Getter
    public static class SignInRequest {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @Getter
    public static class Response {
        private String username;
        private String name;
        private String email;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;
        public Response(User user) {
            this.username = user.getUsername();
            this.name = user.getName();
            this.email = user.getEmail();
            this.createdAt = user.getCreatedAt();
            this.updatedAt = user.getUpdatedAt();
        }
    }
}
