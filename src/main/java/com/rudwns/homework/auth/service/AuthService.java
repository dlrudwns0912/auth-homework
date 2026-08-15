package com.rudwns.homework.auth.service;

import com.rudwns.homework.auth.dto.request.LoginRequest;
import com.rudwns.homework.auth.dto.request.SignupRequest;
import com.rudwns.homework.auth.dto.response.TokenResponse;
import com.rudwns.homework.auth.entity.UserRole;
import com.rudwns.homework.auth.entity.UserEntity;
import com.rudwns.homework.auth.jwt.JwtProvider;
import com.rudwns.homework.auth.repository.UserRepository;
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

        String encodedPassword = passwordEncoder.encode(request.getPw());

        UserEntity userEntity = UserEntity.builder()
                .email(request.getEmail())
                .pw(encodedPassword)
                .role(UserRole.USER)
                .build();

        userRepository.save(userEntity);
        return encodedPassword;
    }

    public TokenResponse login(LoginRequest request) {
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 이메일입니다."));

        if (!passwordEncoder.matches(request.getPw(), user.getPw())) {
            throw new RuntimeException("비밀번호가 일치하디 않습니다.");
        }

        String token = jwtProvider.createToken(user.getEmail());

        return new TokenResponse(token);
    }
}
