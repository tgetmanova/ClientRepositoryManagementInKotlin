package com.github.spb.tget.demo.dao;

import com.github.spb.tget.demo.data.Client;
import com.github.spb.tget.demo.data.ContactInformation;
import com.github.spb.tget.demo.repository.dbRepository.ClientDbRepository;
import com.github.spb.tget.demo.repository.dbRepository.ContactInformationDbRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientDao {

    private ClientDbRepository clientDbRepository;
    private ContactInformationDbRepository contactInformationDbRepository;

    public ClientDao() {
        this.clientDbRepository = ClientDbRepository.create();
        this.contactInformationDbRepository = ContactInformationDbRepository.create();
    }

    public Client createRandomClient() {
        Client client = Client.random();
        this.clientDbRepository.addItem(client);
        return client;
    }

    public Client createRandomClientWithContactInformation2() {
        Client client = Client.random();
        Set<ContactInformation> conInfo = new HashSet<>();
        conInfo.add(ContactInformation.random());
        client.setContactInformation(conInfo);

        this.clientDbRepository.addItem(client);

        conInfo.forEach(ci -> {
            ci.setClient(client);
            this.contactInformationDbRepository.addItem(ci);
        });

        return client;
    }

    public Client createClient(Client client) {
        int clientId = this.clientDbRepository.addItemAndGetId(client);
        client.setClientId(clientId);

        if (client.getContactInformation() != null && !client.getContactInformation().isEmpty()) {
            client.getContactInformation().forEach(conInfo -> {
                conInfo.setClient(client);
                this.contactInformationDbRepository.addItem(conInfo);
            });
        }

        return client;
    }

    public void deleteClient(Client client) {
        this.clientDbRepository.deleteItem(client);
    }

    public List<Client> getClients() {
        return this.clientDbRepository.getItems();
    }

    public Client resolveClient(int clientId) {
        return getClients().stream()
                .filter(client -> client.getClientId() == clientId)
                .findFirst()
                .orElse(null);
    }

    public void updateClient(Client updatedClient) {
        this.clientDbRepository.updateItem(updatedClient);
    }
}
