package com.github.spb.tget.demo.controller;

import com.github.spb.tget.demo.converter.ClientConverter;
import com.github.spb.tget.demo.dao.ClientDao;
import com.github.spb.tget.demo.dto.ClientDto;
import com.github.spb.tget.demo.dto.ContactInformationDto;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClientController {

    private ClientDao clientDao = new ClientDao();
    private ClientConverter clientConverter = new ClientConverter();

    @RequestMapping("/clients")
    public List<ClientDto> getClients() {
        return clientDao.getClients().stream()
                .map(client -> clientConverter.toDto(client))
                .collect(Collectors.toList());
    }

    @RequestMapping("/clients/{id}")
    public ClientDto getClient(@PathVariable Integer id) {
        return clientConverter.toDto(clientDao.resolveClient(id));
    }

    @RequestMapping("/clients/{id}/contacts")
    public List<ContactInformationDto> getClientContacts(@PathVariable Integer id) {
        return clientConverter.toDto(clientDao.resolveClient(id)).getContacts();
    }
}
