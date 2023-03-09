package com.example.sprint.service.impl;

import com.example.sprint.model.OrderDetail;
import com.example.sprint.repository.IOderDetailRepository;
import com.example.sprint.service.IOderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OderDetailService implements IOderDetailService {
    @Autowired
    private IOderDetailRepository oderDetailRepository;


    @Override
    public void save(OrderDetail orderDetail) {
        oderDetailRepository.save(orderDetail);
    }

    @Override
    public boolean existsByProduct_IdAndOder_Customer_Id(Integer idProduct, Integer idCustomer) {
        return oderDetailRepository.existsByProduct_IdAndOder_Customer_Id(idProduct, idCustomer);
    }

    @Override
    public void saveOrderDetail(OrderDetail orderDetail) {
        oderDetailRepository.saveOrderDetail(orderDetail);
    }
}
