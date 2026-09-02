package com.rudwns.homework.domain.auth.controller;

import com.rudwns.homework.domain.auth.dto.request.LoginRequest;
import com.rudwns.homework.domain.auth.dto.request.SignupRequest;
import com.rudwns.homework.domain.auth.dto.response.SignupResponse;
import com.rudwns.homework.domain.auth.dto.response.TokenResponse;
import com.rudwns.homework.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")

public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(
            @Valid @RequestBody SignupRequest request
    ) {
        SignupResponse response = authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<@Valid LoginRequest> login(
            @Valid @RequestBody LoginRequest request
    ) {
        TokenResponse response = authService.login(request);
        return ResponseEntity.ok(request);
    }
}
