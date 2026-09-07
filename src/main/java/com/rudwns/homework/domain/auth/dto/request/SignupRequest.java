package com.rudwns.homework.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter

public class SignupRequest {

    @NotBlank(message = "이메일 작성은 필수입니다.")
    @Email(message = "올바른 이메일이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호는 8자 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "닉네임 작성은 필수입니다.")
    private String nickname;
}
