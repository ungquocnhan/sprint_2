package com.example.sprint.repository;

import com.example.sprint.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IOderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    boolean existsByProduct_IdAndOder_Customer_Id(Integer idProduct, Integer idCustomer);

    @Transactional
    @Modifying
    @Query(value = "insert into order_detail (" +
            "oder_id," +
            " product_id," +
            " quantity_buy," +
            " status_pay," +
            " flag_deleted," +
            " create_date," +
            " modify_date," +
            " status_shipping," +
            " price)" +
            " value (" +
            ":#{#orderDetail.oder.id}," +
            " :#{#orderDetail.product.id}," +
            " :#{#orderDetail.quantityBuy}," +
            " :#{#orderDetail.statusPay}," +
            " :#{#orderDetail.flagDeleted}," +
            " :#{#orderDetail.createDate}," +
            " :#{#orderDetail.modifyDate}," +
            " :#{#orderDetail.statusShipping}," +
            " :#{#orderDetail.price})",
            nativeQuery = true)
    void saveOrderDetail(@Param("orderDetail") OrderDetail orderDetail);



}
