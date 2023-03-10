package com.example.sprint.dto;

import java.time.LocalDateTime;

/**
 * A Projection for the {@link com.example.sprint.model.OrderDetail} entity
 */
public interface OrderDetailInfoDto {
    Integer getQuantityBuy();

    LocalDateTime getCreateDate();

    boolean isStatusPay();

    Double getPrice();

    ProductInfoDto getProduct();
}