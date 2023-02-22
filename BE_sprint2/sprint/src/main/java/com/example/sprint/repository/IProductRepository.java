package com.example.sprint.repository;

import com.example.sprint.dto.ProductInfo;
import com.example.sprint.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT p.name, p.price, p.id, fb.name as nameFrequencyBand , cd.name as nameCoverageDensity, uc.name as nameUserConnect,sw.name as nameSpeedWifi , ip.url FROM product p  JOIN frequency_band fb ON p.frequency_band_id = fb.id JOIN coverage_density cd ON p.coverage_density_id = cd.id JOIN user_connect uc ON p.user_connect_id = uc.id JOIN speed_wifi sw ON p.speed_wifi_id = sw.id JOIN image_product ip ON p.id = ip.product_id WHERE p.flag_deleted = false GROUP BY ip.product_id order by ip.id asc",
            countQuery = "SELECT * FROM (SELECT p.name, p.price, p.id, fb.name as nameFrequencyBand, cd.name as nameCoverageDensity, uc.name as nameUserConnect,sw.name as nameSpeedWifi, ip.url FROM product p  JOIN frequency_band fb ON p.frequency_band_id = fb.id JOIN coverage_density cd ON p.coverage_density_id = cd.id JOIN user_connect uc ON p.user_connect_id = uc.id JOIN speed_wifi sw ON p.speed_wifi_id = sw.id JOIN image_product ip ON p.id = ip.product_id WHERE p.flag_deleted = false GROUP BY ip.product_id order by ip.id asc) count ",
            nativeQuery = true)
    Page<ProductInfo> getAll(Pageable pageable);

    @Override
    Optional<Product> findById(Integer integer);
}