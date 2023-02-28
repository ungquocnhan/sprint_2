package com.example.sprint.controller;

import com.example.sprint.dto.ProductInfo;
import com.example.sprint.model.Product;
import com.example.sprint.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/public/products")
@CrossOrigin("*")
public class ProductRestController {
    @Autowired
    private IProductService productService;

    @GetMapping("")
    public ResponseEntity<Page<ProductInfo>> getAll(@PageableDefault(size = 8) Pageable pageable) {
        Page<ProductInfo> productInfoPage = productService.getAll(pageable);
        return new ResponseEntity<>(productInfoPage, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Integer id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }
}
