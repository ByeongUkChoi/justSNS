package com.byeongukchoi.justSNS.user.controller;

import com.byeongukchoi.justSNS.user.dto.UserDto;
import com.byeongukchoi.justSNS.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// TODO: ip 변경 예정
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto.Response registerUser(@Valid @RequestBody UserDto.SignUpRequest signUpRequestDto) {
        UserDto.Response userResponseDto = service.signup(signUpRequestDto);
        if (userResponseDto == null) {
            // TODO: error
            return null;
        }
        return userResponseDto;
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserDto.SignInRequest signInRequest) {
        String jwt = service.signin(signInRequest);
        return ResponseEntity.ok(jwt);
        //return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @GetMapping("check-username/{username}")
    public ResponseEntity duplicateUsername(@PathVariable String username) {
        boolean isDuplicateUsername = service.isDuplicateUsername(username);
        if (isDuplicateUsername) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
