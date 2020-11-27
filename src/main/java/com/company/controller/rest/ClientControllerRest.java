package com.company.controller.rest;

import com.company.controller.dto.ClientDto;
import com.company.controller.dto.DtoMapper;
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
    public List<Client> getClients(@RequestParam(defaultValue = "1") int page) {
        int pageNumber = page > 0 ? page : 1;
        return clientService.getByLastName(pageNumber-1);
    }

    @GetMapping(path = "/first",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> getClientsByFirstName(@RequestParam(defaultValue="Wojtek") String firstName) {
//        return clientService.getClients(orderBy);
        return clientService.getAllByFirstName(firstName);
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

    // DTO CLIENT

    @GetMapping(path = "/dto", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientDto> getDtoClients(@RequestParam(defaultValue = "1") int page) {
        int pageNumber = page > 0 ? page : 1;
        return DtoMapper.mapToClientDtos(clientService.getByLastName(pageNumber-1));
    }

}

