package com.rudwns.homework.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class SignupRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
