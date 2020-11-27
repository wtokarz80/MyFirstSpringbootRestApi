package com.company.controller.dto;

import com.company.model.Client;

import javax.persistence.Column;

public class ClientDto {

    @Column(name = "id_client")
    private Long id;
    @Column(name="firstname", nullable=false)
    private String firstName;
    @Column(name="lastname", nullable=false)
    private String lastName;
    @Column(nullable = false)
    private String address;

    public ClientDto(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public ClientDto(){}

    public Long getId() {
        return id;
    }

    public ClientDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ClientDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ClientDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ClientDto setAddress(String address) {
        this.address = address;
        return this;
    }
}