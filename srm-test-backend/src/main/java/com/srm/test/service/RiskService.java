package com.srm.test.service;

import com.srm.test.dao.RiskDao;
import com.srm.test.entity.Risk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskService {
    @Autowired
    private RiskDao riskDao;

    public List<Risk> getRiskTypes() {
        return this.riskDao.findAll();
    }
}
