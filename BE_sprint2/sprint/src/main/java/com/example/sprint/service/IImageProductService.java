package com.example.sprint.service;

import com.example.sprint.dto.ProductInfo;
import com.example.sprint.model.ImageProduct;

import java.util.List;

public interface IImageProductService {
    List<ProductInfo> getAll();
}
