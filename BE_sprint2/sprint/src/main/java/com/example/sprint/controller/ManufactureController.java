package com.example.sprint.controller;

import com.example.sprint.model.Manufacture;
import com.example.sprint.service.IManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/public/manufactures")
@CrossOrigin("*")
public class ManufactureController {
    @Autowired
    private IManufactureService manufactureService;

    @GetMapping("")
    public ResponseEntity<List<Manufacture>> getAll() {
        List<Manufacture> manufactureList = manufactureService.findAll();
        return new ResponseEntity<>(manufactureList, HttpStatus.OK);
    }
}
