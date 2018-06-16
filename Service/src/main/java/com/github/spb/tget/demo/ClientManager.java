package com.github.spb.tget.demo;

import com.github.spb.tget.demo.converter.ClientConverter;
import com.github.spb.tget.demo.dao.ClientDao;
import com.github.spb.tget.demo.dao.ContactDao;
import com.github.spb.tget.demo.data.Client;
import com.github.spb.tget.demo.dto.ClientDto;
import com.github.spb.tget.demo.dto.ContactInformationDto;

import java.util.List;
import java.util.stream.Collectors;

public class ClientManager {

    private ClientDao clientDao = new ClientDao();
    private ContactDao contactDao = new ContactDao();
    private ClientConverter clientConverter = new ClientConverter();

    public List<ClientDto> getClients() {
        return clientDao.getClients().stream()
                .map(client -> clientConverter.toDto(client))
                .collect(Collectors.toList());
    }

    public ClientDto getClient(Integer id) {
        return clientConverter.toDto(clientDao.resolveClient(id));
    }

    public List<ContactInformationDto> getClientContacts(Integer id) {
        return clientConverter.toDto(clientDao.resolveClient(id)).getContacts();
    }

    public ClientDto createClient(ClientDto client) {
        return clientConverter.toDto(clientDao.createClient(clientConverter.fromDto(client)));
    }

    public void addContacts(List<ContactInformationDto> contacts, Integer id) {
        Client client = clientDao.resolveClient(id);
        contactDao.addContacts(contacts.stream().map(c -> clientConverter.contactInfoFromDto(c))
                .collect(Collectors.toList()), client);
    }

    public void deleteClient(Integer id) {
        Client client = clientDao.resolveClient(id);
        if (client == null) {
            throw new IllegalStateException("Client is not found, ID: " + id);
        }
        clientDao.deleteClient(client);
    }
}
