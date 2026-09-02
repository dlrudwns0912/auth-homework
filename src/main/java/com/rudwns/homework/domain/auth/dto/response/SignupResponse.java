package com.rudwns.homework.domain.auth.dto.response;

import com.rudwns.homework.domain.auth.entity.UserEntity;
import com.rudwns.homework.domain.auth.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder

public class SignupResponse {
    private Long id;
    private String email;
    private String nickname;
    private UserRole role;

    public static SignupResponse from(UserEntity savedUser) {
        return SignupResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .role(user.getRole())
                .build();
    }
}
