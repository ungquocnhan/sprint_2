package com.example.sprint.repository;

import com.example.sprint.dto.OderInfo;
import com.example.sprint.model.Oder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOderRepository extends JpaRepository<Oder, Integer> {
    @Query("select o from Oder o where o.customer.id = ?1")
    List<OderInfo> getHistoryPay(Integer idCustomer);
}
