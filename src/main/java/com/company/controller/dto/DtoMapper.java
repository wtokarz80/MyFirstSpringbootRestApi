package com.company.controller.dto;

import com.company.model.Client;

import java.util.List;
import java.util.stream.Collectors;

public class DtoMapper {

    private DtoMapper(){}

    public static List<ClientDto> mapToClientDtos(List<Client> clients) {
        return clients.stream().map(DtoMapper::mapClientToDto).collect(Collectors.toList());
    }

    public static ClientDto mapClientToDto(Client client) {
        return new ClientDto().setId(client.getId())
                .setFirstName(client.getFirstName())
                .setLastName(client.getLastName())
                .setAddress(client.getAddress());
    }
}