package com.example.sprint.repository;

import com.example.sprint.model.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IManufactureRepository extends JpaRepository<Manufacture, Integer> {
    List<Manufacture> findAll();
}
