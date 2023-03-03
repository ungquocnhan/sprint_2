package com.example.sprint.controller;

import com.example.sprint.dto.ProductInfo;
import com.example.sprint.model.ImageProduct;
import com.example.sprint.service.IImageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/images")
@CrossOrigin("*")
public class ImageProductController {
    @Autowired
    private IImageProductService imageProductService;

    @GetMapping("")
    public ResponseEntity<List<ProductInfo>> getAll() {
        List<ProductInfo> imageProductList = imageProductService.getAll();
        return new ResponseEntity<>(imageProductList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<ImageProduct>> getImageProductById(@PathVariable("id") Integer id) {
        List<ImageProduct> imageProductList = imageProductService.getImageProductById(id);
        return new ResponseEntity<>(imageProductList, HttpStatus.OK);
    }
}
