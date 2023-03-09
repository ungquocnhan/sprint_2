package com.example.sprint.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PayDto {
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private Integer idCustomer;
    private Double total;
    private String note;
}
