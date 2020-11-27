package com.company.controller.rest;

import com.company.model.Order;
import com.company.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderControllerRest {

    private final OrderService orderService;

    @Autowired
    public OrderControllerRest(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveOrder(@RequestBody Order order) {
        orderService.saveOrder(order);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> editOrder(@RequestBody Order order) {
        return orderService.editOrder(order);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteOrder(@PathVariable long id) {
        orderService.deleteOrder(id);
    }

}