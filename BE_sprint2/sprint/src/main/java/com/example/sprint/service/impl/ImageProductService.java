package com.example.sprint.service.impl;

import com.example.sprint.dto.ProductInfo;
import com.example.sprint.model.ImageProduct;
import com.example.sprint.repository.IImageProductRepository;
import com.example.sprint.service.IImageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImageProductService implements IImageProductService {

    @Autowired
    private IImageProductRepository imageProductRepository;

    @Override
    public List<ProductInfo> getAll() {
        return imageProductRepository.getAll();
    }
}
