package com.example.sprint.service;

import com.example.sprint.model.OrderDetail;


public interface IOderDetailService {
    void save(OrderDetail orderDetail);

    boolean existsByProduct_IdAndOder_Customer_Id(Integer idProduct, Integer idCustomer);

    void saveOrderDetail(OrderDetail orderDetail);
}
