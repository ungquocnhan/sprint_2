package com.example.sprint.controller;

import com.example.sprint.dto.*;
import com.example.sprint.model.*;
import com.example.sprint.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private IOderService oderService;

    @Autowired
    private IOderDetailService oderDetailService;


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
            Optional<CartDetail> cartDetail1 = cartDetailService.findByProduct_IdAndCart_Customer_Id(cartDetailDto.getIdProduct(), cartDetailDto.getIdCustomer());
            if (!cartDetail1.isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            cartDetail1.get().setQuantity(cartDetail1.get().getQuantity() + cartDetailDto.getQuantity());
            cartDetailService.save(cartDetail1.get());
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
    public ResponseEntity<?> getAllCartByIdCustomer(@PathVariable("idCustomer") Integer id) {
        Optional<Customer> customer = customerService.findById(id);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<CartDto> cartDtoList = cartDetailService.getAllCart(id);
        Double totalMoneyCarts = cartDetailService.getTotalMoneyCart(id);
        List<Object> objectList = new ArrayList<>();
        objectList.add(cartDtoList);
        objectList.add(totalMoneyCarts);
        return new ResponseEntity<>(objectList, HttpStatus.OK);
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
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/payment")
    public ResponseEntity<?> paymentCart(@RequestBody PayDto payDto) {
        Oder oder = new Oder();
        OrderDetail orderDetail = new OrderDetail();

        Optional<Customer> customer = customerService.findById(payDto.getIdCustomer());
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        oder.setCustomer(customer.get());
        oder.setNamePay(payDto.getName());
        oder.setPhonePay(payDto.getPhoneNumber());
        oder.setEmailPay(payDto.getEmail());
        oder.setAddressPay(payDto.getAddress());
        oder.setTotalCart(payDto.getTotal());
        oder.setNote(payDto.getNote());
        oder.setCode(ThreadLocalRandom.current().nextInt(0, 999999));
        oderService.save(oder);

        List<CartDto> cartDtoList = cartDetailService.getAllCart(payDto.getIdCustomer());
        List<Product> productList = productService.getAllProduct();
        for (CartDto cartDto : cartDtoList) {
            for (Product product : productList) {
                if (Objects.equals(cartDto.getIdProduct(), product.getId())) {
                    product.setAmountExist(product.getAmountExist() - cartDto.getQuantity());
                }
                productService.save(product);
            }
        }

        for (CartDto cartDto : cartDtoList) {
            Optional<CartDetail> cartDetail = cartDetailService.findByProduct_IdAndCart_Customer_Id(cartDto.getIdProduct(), payDto.getIdCustomer());
            if (!cartDetail.isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            cartDetail.get().setFlagStatus(true);
            cartDetailService.save(cartDetail.get());

//            boolean check = oderDetailService.existsByProduct_IdAndOder_Customer_Id(cartDto.getIdProduct(), payDto.getIdCustomer());
//            if (!check) {
                orderDetail.setProduct(productService.findById(cartDto.getIdProduct()).get());
                orderDetail.setOder(oder);
                orderDetail.setQuantityBuy(cartDto.getQuantity());
                orderDetail.setStatusPay(true);
                orderDetail.setFlagDeleted(false);
                orderDetail.setCreateDate(LocalDateTime.now());
                orderDetail.setModifyDate(LocalDateTime.now());
                orderDetail.setStatusShipping(false);
                orderDetail.setPrice(cartDto.getPrice());
                oderDetailService.saveOrderDetail(orderDetail);
//            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/history/{idCustomer}")
    public ResponseEntity<List<OderInfo>> getHistoryPay(@PathVariable("idCustomer") Integer id) {
        List<OderInfo> oderInfoList = oderService.getHistoryPay(id);
        return new  ResponseEntity<>(oderInfoList, HttpStatus.OK);
    }

    @GetMapping("/detail/{oderId}")
    public ResponseEntity<List<OrderDetailInfoDto>> getHistoryPayDetail(@PathVariable("oderId") Integer id) {
        List<OrderDetailInfoDto> orderDetailInfoList = oderDetailService.getHistoryDetail(id);
        return new ResponseEntity<>(orderDetailInfoList, HttpStatus.OK);
    }
}
