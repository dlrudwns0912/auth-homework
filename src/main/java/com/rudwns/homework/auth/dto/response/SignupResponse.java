package com.rudwns.homework.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class SignupResponse {
    private Long id;
    private String email;
    private String nickname;
}
