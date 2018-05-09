package com.github.spb.tget.demo.controller;

import com.github.spb.tget.demo.converter.ClientConverter;
import com.github.spb.tget.demo.dao.ClientDao;
import com.github.spb.tget.demo.dto.ClientDto;
import com.github.spb.tget.demo.dto.ContactInformationDto;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClientController {

    private ClientDao clientDao = new ClientDao();
    private ClientConverter clientConverter = new ClientConverter();

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public List<ClientDto> getClients() {
        return clientDao.getClients().stream()
                .map(client -> clientConverter.toDto(client))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    public ClientDto getClient(@PathVariable Integer id) {
        return clientConverter.toDto(clientDao.resolveClient(id));
    }

    @RequestMapping(value = "/clients/{id}/contacts", method = RequestMethod.GET)
    public List<ContactInformationDto> getClientContacts(@PathVariable Integer id) {
        return clientConverter.toDto(clientDao.resolveClient(id)).getContacts();
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public void createClient(@RequestBody ClientDto client) {
        clientDao.createClient(clientConverter.fromDto(client));
    }
}
