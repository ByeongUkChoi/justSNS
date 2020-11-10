package com.byeongukchoi.justSNS.controller;

import com.byeongukchoi.justSNS.dto.UserDto;
import com.byeongukchoi.justSNS.model.User;
import com.byeongukchoi.justSNS.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto.Response registerUser(@Valid @RequestBody UserDto.SignUpRequest signUpRequestDto) {
        User user = service.signup(signUpRequestDto);
        if (user == null) {
            // TODO: error
            return null;
        }
        return new UserDto.Response(user);
    }
    @PostMapping("/signin")
    public void authenticateUser() {

    }
}
