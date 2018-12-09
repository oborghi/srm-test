package com.srm.test.controller;

import com.srm.test.entity.Client;
import com.srm.test.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Client> getAllClients() {
        return this.clientService.getAllClients();
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addClient(@Valid @RequestBody Client client) {
        HttpHeaders responseHeader = new HttpHeaders();
        return new ResponseEntity<>(this.clientService.addClient(client), responseHeader, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateClient(@Valid @RequestBody Client client) {
        HttpHeaders responseHeader = new HttpHeaders();
        return new ResponseEntity<>(this.clientService.updateClient(client), responseHeader, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Client getClientById(@PathVariable int id) {
        return this.clientService.getClientById(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public void deleteAllClients() {
         this.clientService.deleteAllClients();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteClientById(@PathVariable int id) {
        this.clientService.deleteClientById(id);
    }
}
