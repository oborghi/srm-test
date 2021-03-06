package com.srm.test.service;

import com.srm.test.dao.ClientDao;
import com.srm.test.entity.Client;
import com.srm.test.entity.RiskEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientDao clientDao;

    public List<Client> getAllClients() {
        return this.clientDao.findAll();
    }

    public Client addClient(Client client) {
        return this.clientDao.save(applyRiskFactor(client));
    }

    public Client getClientById(int id) {
        return this.clientDao.findOne(id);
    }

    public Client updateClient(Client client) {
        return this.clientDao.save(applyRiskFactor(client));
    }

    public void deleteClientById(int id) {
        this.clientDao.delete(id);
    }

    public void deleteAllClients() {
        this.clientDao.deleteAll();
    }

    private Client applyRiskFactor(Client client){

        Float tax = 0F;
        Float creditLimit = client.getCreditLimit();

        if(client.getRisk().equals(RiskEnum.B)){
            tax = 10F;
        } else if(client.getRisk().equals(RiskEnum.C)){
            tax = 20F;
        }

        client.setTax(tax);

        return client;
    }
}
