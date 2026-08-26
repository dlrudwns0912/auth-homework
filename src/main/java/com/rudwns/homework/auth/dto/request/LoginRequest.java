package com.rudwns.homework.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter

public class LoginRequest {

    @NotBlank
    @Email(message = "이메일 작성은 필수입니다.")
    private String email;

    @NotBlank(message = "비밀번호 작성은 필수입니다.")
    private String password;
}
