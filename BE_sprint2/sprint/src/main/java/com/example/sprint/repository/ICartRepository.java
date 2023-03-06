package com.example.sprint.repository;

import com.example.sprint.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<Cart, Integer> {
}
