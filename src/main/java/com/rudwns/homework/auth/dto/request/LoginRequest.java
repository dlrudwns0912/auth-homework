package com.rudwns.homework.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class LoginRequest {

    @NotBlank
    @Email(message = "이메일 작성은 필수입니다.")
    private String email;

    @NotBlank
    private String password;
}
