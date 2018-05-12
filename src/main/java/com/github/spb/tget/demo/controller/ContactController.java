package com.github.spb.tget.demo.controller;

import com.github.spb.tget.demo.ClientManager;
import com.github.spb.tget.demo.dto.ContactInformationDto;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactController {

    private static final String resourceUrl = "/clients/{id}/contacts";
    private ClientManager clientManager = new ClientManager();

    @RequestMapping(value = resourceUrl, method = RequestMethod.GET)
    public List<ContactInformationDto> getClientContacts(@PathVariable Integer id) {
        return clientManager.getClientContacts(id);
    }

    @RequestMapping(value = resourceUrl, method = RequestMethod.POST)
    public void addContacts(@RequestBody List<ContactInformationDto> contacts, @PathVariable Integer id) {
        clientManager.addContacts(contacts, id);
    }
}
