package com.rudwns.homework.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter

public class LoginRequest {

    @NotBlank(message = "이메일 작성은 필수입니다.")
    @Email(message = "올바른 이메일이 아닙니다.")
    private String email;

    @NotBlank(message = "올바른 비밀번호가 아닙니다.")
    private String password;
}
