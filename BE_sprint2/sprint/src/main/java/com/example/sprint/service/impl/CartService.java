package com.example.sprint.service.impl;

import com.example.sprint.model.Cart;
import com.example.sprint.repository.ICartRepository;
import com.example.sprint.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICartRepository cartRepository;

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
}
