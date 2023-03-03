package com.example.sprint.controller;

import com.example.sprint.dto.CartDetailDto;
import com.example.sprint.model.CartDetail;
import com.example.sprint.model.Product;
import com.example.sprint.service.ICartService;
import com.example.sprint.service.ICustomerService;
import com.example.sprint.service.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/user/carts")
@CrossOrigin("*")
public class CartController {
    @Autowired
    private ICartService cartService;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICustomerService customerService;

    @PostMapping("")
    public ResponseEntity<?> addToCart(@RequestBody CartDetailDto cartDetailDto) {
        CartDetail cartDetail = new CartDetail();
        BeanUtils.copyProperties(cartDetailDto, cartDetail);
        Optional<Product> product = productService.findById(cartDetailDto.getIdProduct());
        
        cartService.save(cartDetail);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
