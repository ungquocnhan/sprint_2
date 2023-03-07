package com.example.sprint.repository;

import com.example.sprint.dto.CartDto;
import com.example.sprint.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ICartDetailRepository extends JpaRepository<CartDetail, Integer> {
    boolean existsByProduct_IdAndCart_Customer_Id(Integer idProduct, Integer idCustomer);

    @Query(value = "SELECT cd.id as idCartDetail, cd.quantity, cd.cart_id as idCart, ip.url, p.name as nameProduct, p.price, p.id as idProduct, cu.name as nameCustomer, cu.phone_number as phoneNumber, cu.address from cart_detail cd join product p on cd.product_id = p.id join cart ca on cd.cart_id = ca.id join image_product ip on ip.product_id = p.id join customer cu on ca.customer_id = cu.id where cd.flag_deleted = false AND ca.customer_id = :idCustomer group by cd.cart_id ORDER BY cd.create_date DESC", nativeQuery = true)
    List<CartDto> getAllCart(@Param("idCustomer") Integer idCustomer);

    @Transactional
    @Modifying
    @Query(value = "update cart_detail set quantity = :quantity where id = :idCartDetail", nativeQuery = true)
    void updateAmountInCart(@Param("quantity") Integer quantity,@Param("idCartDetail") Integer idCartDetail);

    Optional<CartDetail> findByProduct_IdAndCart_Customer_Id(Integer idProduct, Integer idCustomer);
}
