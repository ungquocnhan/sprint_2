package com.example.sprint.service.impl;

import com.example.sprint.dto.ProductInfo;
import com.example.sprint.model.Product;
import com.example.sprint.repository.IProductRepository;
import com.example.sprint.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
