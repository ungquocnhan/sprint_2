package com.example.sprint.service.impl;

import com.example.sprint.dto.AmountProductDto;
import com.example.sprint.dto.ProductInfo;
import com.example.sprint.dto.ProductInfoDto;
import com.example.sprint.model.Product;
import com.example.sprint.repository.IProductRepository;
import com.example.sprint.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Page<ProductInfo> getAll(Pageable pageable) {
        return productRepository.getAll(pageable);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Page<ProductInfo> search(String name, Pageable pageable) {
        return productRepository.search(name, name, pageable);
    }

    @Override
    public Page<ProductInfo> getProductPromotion(Pageable pageable) {
        return productRepository.getProductPromotion(pageable);
    }

    @Override
    public Page<ProductInfo> getProductPromotionSpecial(Pageable pageable) {
        return productRepository.getProductPromotionSpecial(pageable);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<ProductInfoDto> getProductBySameManufacture(String nameManufacture, Integer idProduct) {
        return productRepository.getProductBySameManufacture(nameManufacture, idProduct);
    }

    @Override
    public Optional<AmountProductDto> getAmountExist(Integer id) {
        return productRepository.getAmountExist(id);
    }

}
