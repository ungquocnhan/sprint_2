package com.example.sprint.controller;

import com.example.sprint.dto.ProductInfo;
import com.example.sprint.model.ImageProduct;
import com.example.sprint.service.IImageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/images")
@CrossOrigin("*")
public class ImageProductController {
    @Autowired
    private IImageProductService imageProductService;

    @GetMapping("")
    public ResponseEntity<List<ProductInfo>> getAll() {
        List<ProductInfo> imageProductList = imageProductService.getAll();
        return new ResponseEntity<>(imageProductList, HttpStatus.OK);
    }
}
