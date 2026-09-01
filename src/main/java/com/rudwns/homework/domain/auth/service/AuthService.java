package com.rudwns.homework.domain.auth.service;

import com.rudwns.homework.domain.auth.dto.request.LoginRequest;
import com.rudwns.homework.domain.auth.dto.request.SignupRequest;
import com.rudwns.homework.domain.auth.dto.response.TokenResponse;
import com.rudwns.homework.domain.auth.entity.UserRole;
import com.rudwns.homework.domain.auth.entity.UserEntity;
import com.rudwns.homework.global.jwt.JwtProvider;
import com.rudwns.homework.domain.auth.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;


    public String signup(SignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity userEntity = UserEntity.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .role(UserRole.USER)
                .build();

        userRepository.save(userEntity);
        return encodedPassword;
    }

    public TokenResponse login(LoginRequest request) {
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 이메일입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtProvider.createToken(user.getEmail());

        return new TokenResponse(token);
    }
}