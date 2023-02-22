package com.example.sprint.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInForm {
    @Size(min = 3, max = 100)
    private String email;
    @NotBlank
    @Size(min = 6, max = 100)
    private String encryptPassword;
}
