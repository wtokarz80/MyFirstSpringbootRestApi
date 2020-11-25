package com.company.controller.rest;

import com.company.model.Client;
import com.company.model.Order;
import com.company.repository.ClientRepository;
import com.company.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderControllerRest {

    private OrderRepository orderRepository;
    private ClientRepository clientRepository;

    @Autowired
    public OrderControllerRest(OrderRepository orderRepository, ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getOrders(@RequestParam(defaultValue = "id") String orderBy) {
        List<Order> orders = orderRepository.findAll();
        orders.sort(Comparator.comparing(Order::getId));

        return orders;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveOrder(@RequestBody Order order) {
//        order.setClient(clientRepository.getOne(1L));
        orderRepository.save(order);

    }
}