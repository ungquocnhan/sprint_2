package com.example.sprint.service.impl;

import com.example.sprint.dto.CartDto;
import com.example.sprint.dto.TotalMoneyCart;
import com.example.sprint.model.CartDetail;
import com.example.sprint.repository.ICartDetailRepository;
import com.example.sprint.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartDetailService implements ICartDetailService {
    @Autowired
    private ICartDetailRepository cartRepository;

    @Override
    public CartDetail save(CartDetail cartDetail) {
        return cartRepository.save(cartDetail);
    }

    @Override
    public boolean existsByProduct_IdAndCart_Customer_Id(Integer idProduct, Integer idCustomer) {
        return cartRepository.existsByProduct_IdAndCart_Customer_Id(idProduct, idCustomer);
    }

    @Override
    public List<CartDto> getAllCart(Integer idCustomer) {
        return cartRepository.getAllCart(idCustomer);
    }

    @Override
    public void updateAmountInCart(Integer quantity, Integer idCartDetail) {
        cartRepository.updateAmountInCart(quantity, idCartDetail);
    }

    @Override
    public boolean deleteById(Integer idCartDetail) {
        Optional<CartDetail> cartDetail = cartRepository.findById(idCartDetail);
        if (!cartDetail.isPresent()) {
            return false;
        }
        cartRepository.deleteById(idCartDetail);
        return true;
    }

    @Override
    public Optional<CartDetail> findById(Integer idCartDetail) {
        return cartRepository.findById(idCartDetail);
    }

    @Override
    public Optional<CartDetail> findByProduct_IdAndCart_Customer_Id(Integer idProduct, Integer idCustomer) {
        return cartRepository.findByProduct_IdAndCart_Customer_Id(idProduct, idCustomer);
    }

    @Override
    public Double getTotalMoneyCart(Integer idCustomer) {
        return cartRepository.getTotalMoneyCart(idCustomer);
    }


}
