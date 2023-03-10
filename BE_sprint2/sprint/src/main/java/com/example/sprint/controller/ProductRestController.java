package com.example.sprint.controller;

import com.example.sprint.dto.ProductInfo;
import com.example.sprint.dto.ProductInfoDto;
import com.example.sprint.model.Product;
import com.example.sprint.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public/products")
@CrossOrigin("*")
public class ProductRestController {
    @Autowired
    private IProductService productService;

    @GetMapping("")
    public ResponseEntity<Page<ProductInfo>> getAll(@RequestParam(defaultValue = "") String name, @PageableDefault(size = 8) Pageable pageable) {
//        Page<ProductInfo> productInfoPage = productService.getAll(pageable);
        Page<ProductInfo> productInfoPage = productService.search(name, pageable);
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

    @GetMapping("/promotion")
    public ResponseEntity<Page<ProductInfo>> getProductPromotion(@PageableDefault(size = 8) Pageable pageable) {
        Page<ProductInfo> productInfoPage = productService.getProductPromotion(pageable);
        return new ResponseEntity<>(productInfoPage, HttpStatus.OK);
    }

    @GetMapping("/special")
    public ResponseEntity<Page<ProductInfo>> getProductPromotionSpecial(@PageableDefault(size = 8) Pageable pageable) {
        Page<ProductInfo> productInfoPage = productService.getProductPromotionSpecial(pageable);
        return new ResponseEntity<>(productInfoPage, HttpStatus.OK);
    }

    @GetMapping("/sameManufacture")
    public ResponseEntity<List<ProductInfoDto>> getProductSameManufacture(@RequestParam("nameManufacture") String nameManufacture,
                                                                          @RequestParam("idProduct") Integer idProduct) {
        List<ProductInfoDto> productInfoDtoList = productService.getProductBySameManufacture(nameManufacture, idProduct);
        return new ResponseEntity<>(productInfoDtoList, HttpStatus.OK);
    }
}
