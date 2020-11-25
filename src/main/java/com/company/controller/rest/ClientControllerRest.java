package com.company.controller.rest;

import com.company.model.Client;
import com.company.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientControllerRest {

    private final ClientService clientService;

    @Autowired
    public ClientControllerRest(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> getClients(@RequestParam(defaultValue="lastName") String orderBy) {
        return clientService.getClients(orderBy);

    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        return clientService.getClient(id);
    }
    

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveClient(@RequestBody Client client) {
        clientService.saveClient(client);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> editClient(@RequestBody Client client) {
        return clientService.editClient(client);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteClient(@PathVariable long id) {
        clientService.deleteClient(id);
    }


}

