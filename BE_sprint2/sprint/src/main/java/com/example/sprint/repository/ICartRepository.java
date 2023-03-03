package com.example.sprint.repository;

import com.example.sprint.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<CartDetail, Integer> {

}
