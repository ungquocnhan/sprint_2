package com.example.sprint.service.impl;

import com.example.sprint.model.CartDetail;
import com.example.sprint.model.CartRepository;
import com.example.sprint.repository.ICartRepository;
import com.example.sprint.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartRepository cartRepository;

    @Override
    public CartDetail save(CartDetail cartDetail) {
        return cartRepository.save(cartDetail);
    }
}
