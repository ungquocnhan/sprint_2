package com.example.sprint.service.impl;

import com.example.sprint.model.Manufacture;
import com.example.sprint.repository.IManufactureRepository;
import com.example.sprint.service.IManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ManufactureService implements IManufactureService {
    @Autowired
    private IManufactureRepository manufactureRepository;

    @Override
    public List<Manufacture> findAll() {
        return manufactureRepository.findAll();
    }
}
