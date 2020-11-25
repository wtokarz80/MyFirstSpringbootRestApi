package com.company.controller.rest;

import com.company.model.Client;
import com.company.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientControllerRest {

    private ClientRepository clientRepository;

    @Autowired
    public ClientControllerRest(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> getClients(@RequestParam(defaultValue="lastName") String orderBy) {
        List<Client> clients = clientRepository.findAll();
        if("lastName".equals(orderBy)) {
            clients.sort(Comparator.comparing(Client::getLastName));
        } else if("firstName".equals(orderBy)) {
            clients.sort(Comparator.comparing(Client::getFirstName));
        }
        return clients;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveClient(@RequestBody Client client) {
        clientRepository.save(client);
    }


}

