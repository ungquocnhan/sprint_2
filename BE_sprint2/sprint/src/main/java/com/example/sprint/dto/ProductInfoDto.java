package com.example.sprint.dto;

import java.util.List;

/**
 * A Projection for the {@link com.example.sprint.model.Product} entity
 */
public interface ProductInfoDto {
    Integer getId();

    String getName();

    List<ImageProductInfo> getImageProducts();
}