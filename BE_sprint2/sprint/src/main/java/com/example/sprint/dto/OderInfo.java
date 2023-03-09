package com.example.sprint.dto;

import java.util.Date;

/**
 * A Projection for the {@link com.example.sprint.model.Oder} entity
 */
public interface OderInfo {
    Integer getId();

    Integer getCode();

    String getNamePay();

    String getAddressPay();

    Double getTotalCart();

    Date getCreateDate();
}