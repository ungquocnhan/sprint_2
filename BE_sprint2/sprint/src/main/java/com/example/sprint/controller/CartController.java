package com.example.sprint.controller;

import com.example.sprint.dto.CartDetailDto;
import com.example.sprint.dto.CartDto;
import com.example.sprint.model.Cart;
import com.example.sprint.model.CartDetail;
import com.example.sprint.model.Customer;
import com.example.sprint.model.Product;
import com.example.sprint.service.ICartDetailService;
import com.example.sprint.service.ICartService;
import com.example.sprint.service.ICustomerService;
import com.example.sprint.service.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user/carts")
@CrossOrigin("*")
public class CartController {
    @Autowired
    private ICartDetailService cartDetailService;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ICartService cartService;

    @PostMapping("")
    public ResponseEntity<?> addToCart(@RequestBody CartDetailDto cartDetailDto) {
        CartDetail cartDetail = new CartDetail();
        Cart cart = new Cart();
        Optional<Customer> customer = customerService.findById(cartDetailDto.getIdCustomer());
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(cartDetailDto, cartDetail);
        Optional<Product> product = productService.findById(cartDetailDto.getIdProduct());
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        boolean check = cartDetailService.existsByProduct_IdAndCart_Customer_Id(cartDetailDto.getIdProduct(), cartDetailDto.getIdCustomer());
        if (check) {
            cartDetail.setQuantity(cartDetail.getQuantity() + cartDetailDto.getQuantity());
            cartDetailService.save(cartDetail);
//            cartDetailService.updateAmount(cartDetailDto.getQuantity(), cartDetailDto.getIdProduct(), cartDetailDto.getIdCustomer());
//            cartDetailService.save(cartDetail);
        } else {
            cart.setCustomer(customer.get());
            cartService.save(cart);
            cartDetail.setProduct(product.get());
            cartDetail.setCart(cart);
            cartDetailService.save(cartDetail);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{idCustomer}")
    public ResponseEntity<List<CartDto>> getAllCartByIdCustomer(@PathVariable("idCustomer") Integer id) {
        Optional<Customer> customer = customerService.findById(id);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<CartDto> cartDtoList = cartDetailService.getAllCart(id);
        return new ResponseEntity<>(cartDtoList, HttpStatus.OK);
    }

    @PatchMapping("")
    public ResponseEntity<?> updateAmountInCart(@RequestBody CartDetailDto cartDetailDto) {
        cartDetailService.updateAmountInCart(cartDetailDto.getQuantity(), cartDetailDto.getIdCartDetail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{idCartDetail}")
    public ResponseEntity<CartDetail> delete(@PathVariable("idCartDetail") Integer id) {
        Optional<CartDetail> cartDetail = cartDetailService.findById(id);
        if (!cartDetail.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean flagDelete = cartDetailService.deleteById(cartDetail.get().getId());
        if (flagDelete) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
