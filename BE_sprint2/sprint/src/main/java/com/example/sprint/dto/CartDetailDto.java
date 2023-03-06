package com.example.sprint.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailDto {
    private Integer quantity;
    private Integer idProduct;
    private Integer idCustomer;
    private Integer idCartDetail;
}
