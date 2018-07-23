package com.github.spb.tget.demo.dao;

import com.github.spb.tget.demo.data.Client;
import com.github.spb.tget.demo.data.ContactInformation;
import com.github.spb.tget.demo.repository.Repository;
import com.github.spb.tget.demo.repository.RepositoryFactory;
import com.github.spb.tget.demo.repository.inplaceRepository.InplaceRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientDao {

    private Repository clientRepository;
    private Repository contactInformationRepository;
    private String repoType = System.getProperty("repoType");

    public ClientDao() {
        clientRepository = RepositoryFactory.Companion.getClientRepositoryByType(repoType);
        contactInformationRepository = RepositoryFactory.Companion.getContactInformationRepositoryByType(repoType);
    }

    public Client createRandomClient() {
        Client client = Client.random();
        this.clientRepository.addItem(client);
        return client;
    }

    public Client createRandomClientWithContactInformation() {
        Client client = Client.random();
        Set<ContactInformation> conInfo = new HashSet<>();
        conInfo.add(ContactInformation.random());
        client.setContactInformation(conInfo);

        this.clientRepository.addItem(client);

        conInfo.forEach(ci -> {
            ci.setClient(client);
            this.contactInformationRepository.addItem(ci);
        });

        return client;
    }

    public Client createClient(Client client) {
        int clientId = this.clientRepository.addItemAndGetId(client);
        client.setClientId(clientId);

        if (client.getContactInformation() != null && !client.getContactInformation().isEmpty()) {
            client.getContactInformation().forEach(conInfo -> {
                conInfo.setClient(client);
                this.contactInformationRepository.addItem(conInfo);
            });
        }

        return client;
    }

    public void deleteClient(Client client) {
        this.clientRepository.deleteItem(client);
        if (this.clientRepository instanceof InplaceRepository
                && client.getContactInformation() != null
                && !client.getContactInformation().isEmpty()) {
            client.getContactInformation().forEach(
                    contact -> this.contactInformationRepository.deleteItem(contact));
        }
    }

    public List<Client> getClients() {
        return this.clientRepository.getItems();
    }

    public Client resolveClient(int clientId) {
        return getClients().stream()
                .filter(client -> client.getClientId() == clientId)
                .findFirst()
                .orElse(null);
    }

    public void updateClient(Client updatedClient) {
        this.clientRepository.updateItem(updatedClient);
    }
}