package com.example.sprint.service;

import com.example.sprint.dto.OrderDetailInfoDto;
import com.example.sprint.model.OrderDetail;

import java.util.List;


public interface IOderDetailService {
    void save(OrderDetail orderDetail);

    boolean existsByProduct_IdAndOder_Customer_Id(Integer idProduct, Integer idCustomer);

    void saveOrderDetail(OrderDetail orderDetail);

    List<OrderDetailInfoDto> getHistoryDetail(Integer id);
}
