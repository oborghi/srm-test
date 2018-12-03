package com.srm.test.service;

import com.srm.test.dao.ClientTypeDao;
import com.srm.test.entity.ClientType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientTypeService {
    @Autowired
    private ClientTypeDao clientTypeDao;

    public List<ClientType> getAllClientTypes() {
        return this.clientTypeDao.findAll();
    }
}
