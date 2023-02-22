package com.example.sprint.dto;

import com.example.sprint.model.Account;
import com.example.sprint.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Integer id;
    @NotBlank(message = "Không được để trống.")
    private String name;
    @NotBlank(message = "Không được để trống.")
    private String email;
    @NotBlank(message = "Không được để trống.")
    private String address;
    @NotBlank(message = "Không được để trống.")
    private String idNumber;
    @Column(columnDefinition = "bit")
    private boolean gender;
    private String birthday;
    private boolean flagDeleted;
    @NotBlank(message = "Không được để trống.")
    private String phoneNumber;
    private Account account;
    private String encryptPassword;
}
