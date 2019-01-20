package com.srm.test.controller;

import com.srm.test.entity.Risk;
import com.srm.test.service.RiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/risk")
public class RiskController {
    @Autowired
    private RiskService riskService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Risk> getAllRisk() {
        return this.riskService.getRiskTypes();
    }
}
