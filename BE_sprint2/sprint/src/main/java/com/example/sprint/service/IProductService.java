package com.example.sprint.service;

import com.example.sprint.dto.ProductInfo;
import com.example.sprint.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IProductService {
    Page<ProductInfo> getAll(Pageable pageable);

    Optional<Product> findById(Integer id);

    Page<ProductInfo> search(String name, Pageable pageable);

    Page<ProductInfo> getProductPromotion(Pageable pageable);

    Page<ProductInfo> getProductPromotionSpecial(Pageable pageable);

}
