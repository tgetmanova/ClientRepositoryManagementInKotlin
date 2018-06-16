package com.github.spb.tget.demo.controller;

import com.github.spb.tget.demo.ClientManager;
import com.github.spb.tget.demo.dto.ClientDto;;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    private ClientManager clientManager = new ClientManager();

    private static final String resourceUrl = "/clients";

    @RequestMapping(value = resourceUrl, method = RequestMethod.GET)
    public List<ClientDto> getClients() {
        return clientManager.getClients();
    }

    @RequestMapping(value = resourceUrl + "/{id}", method = RequestMethod.GET)
    public ClientDto getClient(@PathVariable Integer id) {
        return clientManager.getClient(id);
    }

    @RequestMapping(value = resourceUrl, method = RequestMethod.POST)
    public ClientDto createClient(@RequestBody ClientDto client) {
        return clientManager.createClient(client);
    }

    @RequestMapping(value = resourceUrl + "/{id}", method = RequestMethod.DELETE)
    public void deleteClient(@PathVariable Integer id) {
        clientManager.deleteClient(id);
    }
}
