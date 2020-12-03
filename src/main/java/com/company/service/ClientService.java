package com.company.service;

import com.company.model.Client;
import com.company.model.Order;
import com.company.model.Product;
import com.company.repository.ClientRepository;
import com.company.repository.OrderRepository;
import com.company.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private static final int PAGE_SIZE = 10;


    @Autowired
    public ClientService(ClientRepository clientRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // tu sortowanie jest juz zrobione przez lastName ale mozna tez dodac przy paginacji (tu po id):

    public List<Client> getByLastName(int page, Sort.Direction sort) {
        List<Client> clients = clientRepository.findAllClients(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")));
        return clients;
    }

    public List<Client> getByLastNameWithOrders(int page, Sort.Direction sort) {
        List<Client> clients = clientRepository.findAllClients(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")));
        List<Long> ids = clients.stream().map(Client::getId).collect(Collectors.toList());
        List<Order> orders = orderRepository.findAllByIdIn(ids);
        clients.forEach(client -> client.setOrders(extractOrders(orders, client.getId())));
        return clients;
    }

    private List<Order> extractOrders(List<Order> orders, Long id) {
        return orders.stream().filter(order -> order.getClientId().equals(id)).collect(Collectors.toList());
    }


    public ResponseEntity<Client> getClient(Long id) {
        return clientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    // zeby miec tylko jedna transakcje dodajemy tą adnotacje
    @Transactional
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

        // metoda save w tym miejscu nie jest juz potrzebna bo hibernate z automatu to zrobi

//        clientRepository.save(editedClient);
    }

    public void deleteClient(long id) {
        clientRepository.deleteById(id);
    }

    public List<Client> getAllByFirstName(String firstName) {
        List<Client> clients = clientRepository.findAllByFirstName(firstName);
        return clients;
    }


}


