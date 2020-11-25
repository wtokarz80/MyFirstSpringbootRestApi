package com.company.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "client_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id_order")
//    private Long id;
//    @ManyToMany(fetch = FetchType.EAGER,
//            cascade = CascadeType.PERSIST)
//    @Fetch(FetchMode.SELECT)
//    @JoinTable(name = "order_products",
//            joinColumns = { @JoinColumn(name = "order_id", referencedColumnName = "id_order") },
//            inverseJoinColumns = { @JoinColumn(name = "product_id", referencedColumnName = "id_product") })
//    private List<Product> products = new ArrayList<>();
//    @Column(name = "details", length = 512)
//    private String orderDetails;
//    @ManyToOne
//    @JoinColumn(name = "client_id")
//    private Client client;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long id;
    @Column(name = "details", length = 512)
    private String orderDetails;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "order_products",
            joinColumns = { @JoinColumn(name = "order_id", referencedColumnName = "id_order") },
            inverseJoinColumns = { @JoinColumn(name = "product_id", referencedColumnName = "id_product") })
    private List<Product> products = new ArrayList<>();


    public Order() {
    }

    public Order(String orderDetails) {
        super();
        this.orderDetails = orderDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(orderDetails, order.orderDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDetails);
    }


//    @Override
//    public String toString() {
//        return "Order [id=" + id
//                + ", orderDetails=" + orderDetails
//                + ", client=" + client.getFirstName() + " " + client.getLastName() + products.size()
//                + ",\n products=" + products + "]";
//    }


}