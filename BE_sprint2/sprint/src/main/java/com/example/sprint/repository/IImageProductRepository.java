package com.example.sprint.repository;

import com.example.sprint.dto.ProductInfo;
import com.example.sprint.model.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IImageProductRepository extends JpaRepository<ImageProduct, Integer> {
    @Query(value = "SELECT ip.url, p.name, p.id FROM image_product ip JOIN product p ON p.id = ip.product_id GROUP BY product_id ORDER BY product_id LIMIT 10", nativeQuery = true)
    List<ProductInfo> getAll();

    @Query(value = "SELECT * FROM image_product WHERE product_id = :productId ", nativeQuery = true)
    List<ImageProduct> getImageProductById(@Param("productId") Integer id);

}
