package com.example.sprint.service;

import com.example.sprint.dto.CartDto;
import com.example.sprint.model.CartDetail;


import java.util.List;
import java.util.Optional;

public interface ICartDetailService {
    CartDetail save(CartDetail cartDetail);

    boolean existsByProduct_IdAndCart_Customer_Id(Integer idProduct, Integer idCustomer);

    List<CartDto> getAllCart(Integer idCustomer);

    void updateAmountInCart(Integer quantity, Integer idCartDetail);

    boolean deleteById(Integer idCartDetail);

    Optional<CartDetail> findById(Integer idCartDetail);

    Optional<CartDetail> findByProduct_IdAndCart_Customer_Id(Integer idProduct, Integer idCustomer);
}
