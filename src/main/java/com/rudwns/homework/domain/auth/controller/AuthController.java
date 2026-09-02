package com.rudwns.homework.domain.auth.controller;

import com.rudwns.homework.domain.auth.dto.request.LoginRequest;
import com.rudwns.homework.domain.auth.dto.request.SignupRequest;
import com.rudwns.homework.domain.auth.dto.response.TokenResponse;
import com.rudwns.homework.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")

public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
