package com.example.sprint.service.impl;

import com.example.sprint.dto.OderInfo;
import com.example.sprint.model.Oder;
import com.example.sprint.repository.IOderRepository;
import com.example.sprint.service.IOderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OderService implements IOderService {
    @Autowired
    private IOderRepository oderRepository;

    @Override
    public void save(Oder oder) {
        oderRepository.save(oder);
    }

    @Override
    public List<OderInfo> getHistoryPay(Integer idCustomer) {
        return oderRepository.getHistoryPay(idCustomer);
    }
}
