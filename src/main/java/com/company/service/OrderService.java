package com.company.service;


import com.company.model.Client;
import com.company.model.Order;
import com.company.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        List<Order> orders = orderRepository.findAll();
        orders.sort(Comparator.comparing(Order::getId));

        return orders;
    }

    public ResponseEntity<Order> getOrder(Long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }


    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }

    public ResponseEntity<Order> editOrder(Order order) {
        Optional<Order> optionalOrder = orderRepository.findById(order.getId());
        optionalOrder.ifPresentOrElse(or -> updateOrder(or, order), optionalOrder::orElseThrow);
        return ResponseEntity.accepted().build();
    }

    private void updateOrder(Order editedOrder, Order newOrder) {
        editedOrder.setOrderDetails(newOrder.getOrderDetails());
        editedOrder.setProducts(newOrder.getProducts());
        orderRepository.save(editedOrder);
    }
}
