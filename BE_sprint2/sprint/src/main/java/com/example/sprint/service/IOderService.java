package com.example.sprint.service;

import com.example.sprint.dto.OderInfo;
import com.example.sprint.model.Oder;

import java.util.List;

public interface IOderService {
    void save(Oder oder);

    List<OderInfo> getHistoryPay(Integer idCustomer);
}
