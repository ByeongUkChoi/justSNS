package com.byeongukchoi.justSNS.service;

import com.byeongukchoi.justSNS.dto.UserDto;
import com.byeongukchoi.justSNS.model.User;
import com.byeongukchoi.justSNS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public User signup(UserDto.SignUpRequest signUpRequestDto) {

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

        return user;
    }
    public void signin() {

    }
}
