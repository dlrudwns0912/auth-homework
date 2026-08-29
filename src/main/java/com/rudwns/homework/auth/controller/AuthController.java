package com.rudwns.homework.auth.controller;

import com.rudwns.homework.auth.dto.request.LoginRequest;
import com.rudwns.homework.auth.dto.request.SignupRequest;
import com.rudwns.homework.auth.dto.response.TokenResponse;
import com.rudwns.homework.auth.service.AuthService;
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
