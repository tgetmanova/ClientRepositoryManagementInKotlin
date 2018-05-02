package com.github.spb.tget.demo.managers;

import com.github.spb.tget.demo.data.Client;
import com.github.spb.tget.demo.data.ContactInformation;
import com.github.spb.tget.demo.repository.dao.ClientDao;
import com.github.spb.tget.demo.repository.dao.ContactInformationDao;

import java.util.List;

public class ClientManager {

    private ClientDao clientDao;
    private ContactInformationDao contactInformationDao;

    public ClientManager() {
        this.clientDao = ClientDao.create();
        this.contactInformationDao = ContactInformationDao.create();
    }

    public Client createRandomClient() {
        Client client = Client.random();
        this.clientDao.addItem(client);
        return client;
    }

    public Client createRandomClientWithContactInformation() {
        Client client = Client.random();
        ContactInformation conInfo = ContactInformation.random();

        int clientId = this.clientDao.addItemAndGetId(client);
        client.setClientId(clientId);

        conInfo.setClient(client);
        this.contactInformationDao.addItem(conInfo);

        return client;
    }

    public Client createClient(Client client) {
        int clientId = this.clientDao.addItemAndGetId(client);
        client.setClientId(clientId);

        if (client.getContactInformation() != null && !client.getContactInformation().isEmpty()) {
            client.getContactInformation().forEach(conInfo -> {
                conInfo.setClient(client);
                this.contactInformationDao.addItem(conInfo);
            });
        }

        return client;
    }

    public void deleteClient(Client client) {
        this.clientDao.deleteItem(client);
    }

    public List<Client> getClients() {
        return this.clientDao.getItems();
    }

    public Client resolveClient(int clientId) {
        return getClients().stream()
                .filter(client -> client.getClientId() == clientId)
                .findFirst()
                .orElse(null);
    }

    public void updateClient(Client updatedClient) {
        this.clientDao.updateItem(updatedClient);
    }
}
