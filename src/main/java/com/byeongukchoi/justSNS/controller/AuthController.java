package com.byeongukchoi.justSNS.controller;

import com.byeongukchoi.justSNS.dto.UserDto;
import com.byeongukchoi.justSNS.model.User;
import com.byeongukchoi.justSNS.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

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
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserDto.SignInRequest signInRequest) {
        String jwt = service.signin(signInRequest);
        return ResponseEntity.ok(jwt);
        //return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
}
