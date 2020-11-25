package com.company.service;

import com.company.model.Client;
import com.company.repository.ClientRepository;
import com.company.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;


    @Autowired
    public ClientService(ClientRepository clientRepository, OrderRepository orderRepository) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }

    public List<Client> getClients(String orderBy){
        List<Client> clients = clientRepository.findAll();
        if("lastName".equals(orderBy)) {
            clients.sort(Comparator.comparing(Client::getLastName));
        } else if("firstName".equals(orderBy)) {
            clients.sort(Comparator.comparing(Client::getFirstName));
        }
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
        Optional<Client> optionalClient = clientRepository.findById(client.getId());
        optionalClient.ifPresentOrElse(oc -> updateClient(oc, client), optionalClient::orElseThrow);
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
}
//    public Post editPost(Post post) {
//        Post postEdited = postRepository.findById(post.getId()).orElseThrow();
//        postEdited.setTitle(post.getTitle());
//        postEdited.setContent(post.getContent());
//        return postEdited;
//    }