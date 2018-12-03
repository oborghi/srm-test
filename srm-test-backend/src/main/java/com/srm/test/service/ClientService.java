package com.srm.test.service;

import com.srm.test.dao.ClientDao;
import com.srm.test.entity.Client;
import com.srm.test.entity.ClientTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Client> getClientById(int id) {
        return this.clientDao.findById(id);
    }

    public Client updateClient(Client client) {
        return this.clientDao.save(applyRiskFactor(client));
    }

    public void deleteClientById(int id) {
        this.clientDao.deleteById(id);
    }

    public void deleteAllClients() {
        this.clientDao.deleteAll();
    }

    private Client applyRiskFactor(Client client){

        Float percentFactor = 0F;
        Float creditLimit = client.getCreditLimit();

        if(client.getClientType().equals(ClientTypeEnum.B)){
            percentFactor = 10F;
        } else if(client.getClientType().equals(ClientTypeEnum.C)){
            percentFactor = 20F;
        }

        if(!client.getClientType().equals(ClientTypeEnum.A)) {
            Float percentValue = (creditLimit / 100F) * percentFactor;
            creditLimit = creditLimit + percentValue;
            client.setCreditLimit(creditLimit);
        }

        return client;
    }
}
