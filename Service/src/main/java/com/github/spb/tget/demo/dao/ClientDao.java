package com.github.spb.tget.demo.dao;

import com.github.spb.tget.demo.data.ClientEntity;
import com.github.spb.tget.demo.data.ContactEntity;
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

    public ClientEntity createRandomClient() {
        ClientEntity client = ClientEntity.Companion.random();
        this.clientRepository.addItem(client);
        return client;
    }

    public ClientEntity createRandomClientWithContactInformation() {
        ClientEntity client = ClientEntity.Companion.random();
        Set<ContactEntity> conInfo = new HashSet<>();
        conInfo.add(ContactEntity.Companion.random());
        client.setContactInformation(conInfo);

        this.clientRepository.addItem(client);

        conInfo.forEach(ci -> {
            ci.setClient(client);
            this.contactInformationRepository.addItem(ci);
        });

        return client;
    }

    public ClientEntity createClient(ClientEntity client) {
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

    public void deleteClient(ClientEntity client) {
        this.clientRepository.deleteItem(client);
        if (this.clientRepository instanceof InplaceRepository
                && client.getContactInformation() != null
                && !client.getContactInformation().isEmpty()) {
            client.getContactInformation().forEach(
                    contact -> this.contactInformationRepository.deleteItem(contact));
        }
    }

    public List<ClientEntity> getClients() {
        return this.clientRepository.getItems();
    }

    public ClientEntity resolveClient(int clientId) {
        return getClients().stream()
                .filter(client -> client.getClientId() == clientId)
                .findFirst()
                .orElse(null);
    }

    public void updateClient(ClientEntity updatedClient) {
        this.clientRepository.updateItem(updatedClient);
    }
}