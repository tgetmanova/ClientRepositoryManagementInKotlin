package com.github.spb.tget.demo.test;

import com.github.spb.tget.demo.data.Client;
import com.github.spb.tget.demo.data.ContactInformation;
import com.github.spb.tget.demo.managers.ClientManager;
import com.github.spb.tget.demo.managers.ContactManager;
import com.github.spb.tget.demo.util.RandomUtils;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientManagerTest {

    private ClientManager clientManager;
    private ContactManager contactManager;

    public ClientManagerTest() {
        this.clientManager = new ClientManager();
        this.contactManager = new ContactManager();
    }

    @Test
    public void createClient() {
        Client client = clientManager.createRandomClient();

        List<Client> clients = clientManager.getClients();

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(clients)
                .as("Client is not added to the repository")
                .contains(client);

        assertions.assertAll();
    }

    @Test
    public void createClientWithSingleContactInfo() {
        Client client = Client.random().withRandomContactInformation();
        clientManager.createClient(client);

        List<Client> clients = clientManager.getClients();

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(clients)
                .as("Client is not added to the repository")
                .contains(client);

        List<ContactInformation> actualContacts = contactManager.getAllContacts();
        assertions.assertThat(actualContacts)
                .as("Contact information is not added to the repository")
                .containsAll(client.getContactInformation());

        assertions.assertAll();
    }

    @Test
    public void createClientWithMultipleContactInfo() {
        Client client = Client.random();
        Set<ContactInformation> expectedContacts = new HashSet<>();
        expectedContacts.add(ContactInformation.random());
        expectedContacts.add(ContactInformation.random());
        client.withContactInformation(expectedContacts);
        clientManager.createClient(client);

        List<Client> clients = clientManager.getClients();

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(clients)
                .as("Client is not added to the repository")
                .contains(client);

        List<ContactInformation> actualContacts = contactManager.getAllContacts();
        assertions.assertThat(actualContacts)
                .as("Contact information is not added to the repository")
                .containsAll(client.getContactInformation());

        assertions.assertAll();
    }

    @Test
    public void updateClient() {
        Client client = clientManager.createRandomClient();

        List<Client> clients = clientManager.getClients();

        Assertions.assertThat(clients)
                .as("Client is not added to the repository")
                .contains(client);

        Client changedClient = client.withFirstName(RandomUtils.getRandomAlphabetic(5))
                .withLastName(RandomUtils.getRandomAlphabetic(5))
                .withMiddleName(RandomUtils.getRandomAlphabetic(5));
        clientManager.updateClient(changedClient);
        Client updatedClient = clientManager.resolveClient(client.getClientId());

        List<Client> updatedClients = clientManager.getClients();

        Assertions.assertThat(updatedClients)
                .as("Client is not updated in the repository")
                .contains(changedClient);
    }

    @Test
    public void resolveUpdatedClient() {
        Client initialClient = clientManager.createRandomClient();

        List<Client> clients = clientManager.getClients();

        Assertions.assertThat(clients)
                .as("Client is not added to the repository")
                .contains(initialClient);

        Client changedClient = initialClient.withFirstName(RandomUtils.getRandomAlphabetic(5))
                .withLastName(RandomUtils.getRandomAlphabetic(5))
                .withMiddleName(RandomUtils.getRandomAlphabetic(5));
        clientManager.updateClient(changedClient);
        Client updatedClient = clientManager.resolveClient(initialClient.getClientId());

        Assertions.assertThat(updatedClient)
                .as("Client is not resolved as updated in the repository")
                .isEqualTo(changedClient);
    }

    @Test
    public void deleteClient() {
        Client client = clientManager.createRandomClient();

        List<Client> clients = clientManager.getClients();
        Assertions.assertThat(clients)
                .as("Client is not added to the repository")
                .contains(client);

        clientManager.deleteClient(client);
        Client deletedClient = clientManager.resolveClient(client.getClientId());

        List<Client> updatedClients = clientManager.getClients();

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(updatedClients)
                .as("Client must be deleted from the repository")
                .doesNotContain(deletedClient);
        assertions.assertThat(deletedClient)
                .as("Deleted Client should not be resolved in the repository")
                .isNull();
        assertions.assertAll();
    }

    @Test
    public void deleteClientWithContacts() {
        Client client = clientManager.createRandomClientWithContactInformation2();

        List<Client> clients = clientManager.getClients();

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(clients)
                .as("Client is not added to the repository")
                .contains(client);

        List<ContactInformation> actualContacts = contactManager.getAllContacts();
        assertions.assertThat(actualContacts)
                .as("Contact information is not added to the repository")
                .containsAll(client.getContactInformation());

        assertions.assertAll();

        clientManager.deleteClient(client);

        List<Client> updatedClients = clientManager.getClients();
        List<ContactInformation> updatedContacts = contactManager.getAllContacts();

        assertions = new SoftAssertions();
        assertions.assertThat(updatedClients)
                .as("Client must be deleted from the repository")
                .doesNotContain(client);
        assertions.assertThat(updatedContacts)
                .as("Deleted Client contacts must be cleared from the repository")
                .doesNotContainAnyElementsOf(client.getContactInformation());
        assertions.assertAll();
    }
}
