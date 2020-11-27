package com.company.service;

import com.company.model.Client;
import com.company.repository.ClientRepository;
import com.company.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private static final int PAGE_SIZE = 10;


    @Autowired
    public ClientService(ClientRepository clientRepository, OrderRepository orderRepository) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }

    public List<Client> getByLastName(int page){
        List<Client> clients = clientRepository.findAllByOrderByLastNameAsc(PageRequest.of(page, PAGE_SIZE));
        return clients;
    }

    public ResponseEntity<Client> getClient(Long id){
        return clientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public void saveClient(Client client){
        clientRepository.save(client);
    }

    public ResponseEntity<Client> editClient(Client client) {
        Client clientToUpdate = clientRepository.findById(client.getId()).orElseThrow(RuntimeException::new);
        updateClient(clientToUpdate, client);
        //        Optional<Client> optionalClient = clientRepository.findById(client.getId());
//        optionalClient.ifPresentOrElse(oc -> updateClient(oc, client), optionalClient::orElseThrow);
        return ResponseEntity.accepted().build();
    }

    private void updateClient(Client editedClient, Client newClient) {
        editedClient.setFirstName(newClient.getFirstName());
        editedClient.setLastName(newClient.getLastName());
        editedClient.setAddress(newClient.getAddress());
        editedClient.setOrders(newClient.getOrders());
        clientRepository.save(editedClient);
    }

    public void deleteClient(long id) {
        clientRepository.deleteById(id);
    }

    public List<Client> getAllByFirstName(String firstName) {
        List<Client> clients = clientRepository.findAllByFirstName(firstName);
        return clients;
    }
}
