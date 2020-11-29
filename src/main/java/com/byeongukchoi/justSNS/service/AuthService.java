package com.byeongukchoi.justSNS.service;

import com.byeongukchoi.justSNS.dto.UserDto;
import com.byeongukchoi.justSNS.domain.User;
import com.byeongukchoi.justSNS.repository.UserRepository;
import com.byeongukchoi.justSNS.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public UserDto.Response signup(UserDto.SignUpRequest signUpRequestDto) {

        // TODO: DB 제약조건 (unique key) 에러 예외 처리 필요
        User user = userRepository.save(
                User.builder()
                        .username(signUpRequestDto.getUsername())
                        .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                        .name(signUpRequestDto.getName())
                        .email(signUpRequestDto.getEmail())
                        .build()
        );

        if (user == null) {
            // TODO: 에러처리를 하는 부분 만들기
            throw new RuntimeException("test");
        }

        return new UserDto.Response(user);
    }
    public String signin(UserDto.SignInRequest signInRequest) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // ???
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return jwt;
    }
}
