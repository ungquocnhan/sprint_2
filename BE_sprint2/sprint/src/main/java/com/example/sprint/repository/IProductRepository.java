package com.example.sprint.repository;

import com.example.sprint.dto.ProductInfo;
import com.example.sprint.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT p.name, p.price, p.id, fb.name as nameFrequencyBand , cd.name as nameCoverageDensity, uc.name as nameUserConnect,sw.name as nameSpeedWifi , ip.url FROM product p  JOIN frequency_band fb ON p.frequency_band_id = fb.id JOIN coverage_density cd ON p.coverage_density_id = cd.id JOIN user_connect uc ON p.user_connect_id = uc.id JOIN speed_wifi sw ON p.speed_wifi_id = sw.id JOIN image_product ip ON p.id = ip.product_id WHERE p.flag_deleted = false GROUP BY ip.product_id order by ip.id asc",
            countQuery = "SELECT * FROM (SELECT p.name, p.price, p.id, fb.name as nameFrequencyBand, cd.name as nameCoverageDensity, uc.name as nameUserConnect,sw.name as nameSpeedWifi, ip.url FROM product p  JOIN frequency_band fb ON p.frequency_band_id = fb.id JOIN coverage_density cd ON p.coverage_density_id = cd.id JOIN user_connect uc ON p.user_connect_id = uc.id JOIN speed_wifi sw ON p.speed_wifi_id = sw.id JOIN image_product ip ON p.id = ip.product_id WHERE p.flag_deleted = false GROUP BY ip.product_id order by ip.id asc) count ",
            nativeQuery = true)
    Page<ProductInfo> getAll(Pageable pageable);

    @Override
    Optional<Product> findById(Integer integer);

    @Query(value = "SELECT p.name, p.price, p.id, fb.name as nameFrequencyBand , cd.name as nameCoverageDensity, uc.name as nameUserConnect,sw.name as nameSpeedWifi , ip.url, prom.percent_promotion as percentPromotion " +
            "FROM product p  " +
            "JOIN frequency_band fb " +
            "ON p.frequency_band_id = fb.id " +
            "JOIN coverage_density cd " +
            "ON p.coverage_density_id = cd.id " +
            "JOIN user_connect uc " +
            "ON p.user_connect_id = uc.id " +
            "JOIN speed_wifi sw " +
            "ON p.speed_wifi_id = sw.id " +
            "JOIN image_product ip " +
            "ON p.id = ip.product_id " +
            "JOIN manufacture m " +
            "ON m.id = p.manufacture_id " +
            "JOIN promotion prom " +
            "ON p.promotion_id = prom.id " +
            "WHERE p.flag_deleted = false " +
            "AND (p.name LIKE %:nameProduct% OR m.name LIKE %:nameManufacture%)" +
            "GROUP BY ip.product_id " +
            "ORDER BY ip.id desc ",
            countQuery = "SELECT * FROM (" +
                    "SELECT p.name, p.price, p.id, fb.name as nameFrequencyBand, cd.name as nameCoverageDensity, uc.name as nameUserConnect,sw.name as nameSpeedWifi, ip.url, prom.percent_promotion as percentPromotion " +
                    "FROM product p  " +
                    "JOIN frequency_band fb " +
                    "ON p.frequency_band_id = fb.id " +
                    "JOIN coverage_density cd " +
                    "ON p.coverage_density_id = cd.id " +
                    "JOIN user_connect uc " +
                    "ON p.user_connect_id = uc.id " +
                    "JOIN speed_wifi sw " +
                    "ON p.speed_wifi_id = sw.id " +
                    "JOIN image_product ip " +
                    "ON p.id = ip.product_id " +
                    "JOIN manufacture m " +
                    "ON m.id = p.manufacture_id " +
                    "JOIN promotion prom " +
                    "ON p.promotion_id = prom.id " +
                    "WHERE p.flag_deleted = false AND (p.name LIKE %:nameProduct% OR m.name LIKE %:nameManufacture%) " +
                    "GROUP BY ip.product_id " +
                    "ORDER BY ip.id desc) count ",
            nativeQuery = true)
    Page<ProductInfo> search(@Param("nameProduct") String nameProduct, @Param("nameManufacture") String nameManufacture, Pageable pageable);

    @Query(value = "SELECT p.name, p.price, p.id, fb.name as nameFrequencyBand , cd.name as nameCoverageDensity, uc.name as nameUserConnect,sw.name as nameSpeedWifi , ip.url, m.name as nameManufacture, prom.percent_promotion as percentPromotion " +
            "FROM product p  " +
            "JOIN frequency_band fb " +
            "ON p.frequency_band_id = fb.id " +
            "JOIN coverage_density cd " +
            "ON p.coverage_density_id = cd.id " +
            "JOIN user_connect uc " +
            "ON p.user_connect_id = uc.id " +
            "JOIN speed_wifi sw " +
            "ON p.speed_wifi_id = sw.id " +
            "JOIN image_product ip " +
            "ON p.id = ip.product_id " +
            "JOIN manufacture m " +
            "ON m.id = p.manufacture_id " +
            "JOIN promotion prom " +
            "ON p.promotion_id = prom.id " +
            "WHERE p.flag_deleted = false AND p.flag_promoted = true " +
            "GROUP BY ip.product_id " +
            "ORDER BY ip.id desc ",
            countQuery = "SELECT * FROM (" +
                    "SELECT p.name, p.price, p.id, fb.name as nameFrequencyBand, cd.name as nameCoverageDensity, uc.name as nameUserConnect,sw.name as nameSpeedWifi, ip.url, m.name as nameManufacture, prom.percent_promotion as percentPromotion " +
                    "FROM product p  " +
                    "JOIN frequency_band fb " +
                    "ON p.frequency_band_id = fb.id " +
                    "JOIN coverage_density cd " +
                    "ON p.coverage_density_id = cd.id " +
                    "JOIN user_connect uc " +
                    "ON p.user_connect_id = uc.id " +
                    "JOIN speed_wifi sw " +
                    "ON p.speed_wifi_id = sw.id " +
                    "JOIN image_product ip " +
                    "ON p.id = ip.product_id " +
                    "JOIN manufacture m " +
                    "ON m.id = p.manufacture_id " +
                    "JOIN promotion prom " +
                    "ON p.promotion_id = prom.id " +
                    "WHERE p.flag_deleted = false AND p.flag_promoted = true " +
                    "GROUP BY ip.product_id " +
                    "ORDER BY ip.id desc) count ",
            nativeQuery = true)
    Page<ProductInfo> getProductPromotion(Pageable pageable);

    @Query(value = "SELECT p.name, p.price, p.id, fb.name as nameFrequencyBand , cd.name as nameCoverageDensity, uc.name as nameUserConnect,sw.name as nameSpeedWifi , ip.url, m.name as nameManufacture, prom.percent_promotion as percentPromotion " +
            "FROM product p  " +
            "JOIN frequency_band fb " +
            "ON p.frequency_band_id = fb.id " +
            "JOIN coverage_density cd " +
            "ON p.coverage_density_id = cd.id " +
            "JOIN user_connect uc " +
            "ON p.user_connect_id = uc.id " +
            "JOIN speed_wifi sw " +
            "ON p.speed_wifi_id = sw.id " +
            "JOIN image_product ip " +
            "ON p.id = ip.product_id " +
            "JOIN manufacture m " +
            "ON m.id = p.manufacture_id " +
            "JOIN promotion prom " +
            "ON p.promotion_id = prom.id " +
            "WHERE p.flag_deleted = false AND p.flag_promoted = true AND prom.percent_promotion >= 0.3 " +
            "GROUP BY ip.product_id " +
            "ORDER BY ip.id desc ",
            countQuery = "SELECT * FROM (" +
                    "SELECT p.name, p.price, p.id, fb.name as nameFrequencyBand, cd.name as nameCoverageDensity, uc.name as nameUserConnect,sw.name as nameSpeedWifi, ip.url, m.name as nameManufacture, prom.percent_promotion as percentPromotion " +
                    "FROM product p  " +
                    "JOIN frequency_band fb " +
                    "ON p.frequency_band_id = fb.id " +
                    "JOIN coverage_density cd " +
                    "ON p.coverage_density_id = cd.id " +
                    "JOIN user_connect uc " +
                    "ON p.user_connect_id = uc.id " +
                    "JOIN speed_wifi sw " +
                    "ON p.speed_wifi_id = sw.id " +
                    "JOIN image_product ip " +
                    "ON p.id = ip.product_id " +
                    "JOIN manufacture m " +
                    "ON m.id = p.manufacture_id " +
                    "JOIN promotion prom " +
                    "ON p.promotion_id = prom.id " +
                    "WHERE p.flag_deleted = false AND p.flag_promoted = true AND prom.percent_promotion >= 0.3" +
                    "GROUP BY ip.product_id " +
                    "ORDER BY ip.id desc) count ",
            nativeQuery = true)
    Page<ProductInfo> getProductPromotionSpecial(Pageable pageable);
}