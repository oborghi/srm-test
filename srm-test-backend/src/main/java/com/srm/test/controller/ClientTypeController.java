package com.srm.test.controller;

import com.srm.test.entity.ClientType;
import com.srm.test.service.ClientTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/clientType")
public class ClientTypeController {
    @Autowired
    private ClientTypeService clientTypeService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<ClientType> getAllClientTypes() {
        return this.clientTypeService.getAllClientTypes();
    }
}
