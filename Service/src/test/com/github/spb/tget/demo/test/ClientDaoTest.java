package com.github.spb.tget.demo.test;

import com.github.spb.tget.demo.data.Client;
import com.github.spb.tget.demo.data.ContactInformation;
import com.github.spb.tget.demo.dao.ClientDao;
import com.github.spb.tget.demo.dao.ContactDao;
import com.github.spb.tget.demo.util.RandomUtils;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientDaoTest {

    private ClientDao clientDao;
    private ContactDao contactDao;

    public ClientDaoTest() {
        this.clientDao = new ClientDao();
        this.contactDao = new ContactDao();
    }

    @Test
    public void createClient() {
        Client client = clientDao.createRandomClient();

        List<Client> clients = clientDao.getClients();

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(clients)
                .as("Client is not added to the repository")
                .contains(client);

        assertions.assertAll();
    }

    @Test
    public void createClientWithSingleContactInfo() {
        Client client = Client.random().withRandomContactInformation();
        clientDao.createClient(client);

        List<Client> clients = clientDao.getClients();

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(clients)
                .as("Client is not added to the repository")
                .contains(client);

        List<ContactInformation> actualContacts = contactDao.getAllContacts();
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
        clientDao.createClient(client);

        List<Client> clients = clientDao.getClients();

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(clients)
                .as("Client is not added to the repository")
                .contains(client);

        List<ContactInformation> actualContacts = contactDao.getAllContacts();
        assertions.assertThat(actualContacts)
                .as("Contact information is not added to the repository")
                .containsAll(client.getContactInformation());

        assertions.assertAll();
    }

    @Test
    public void updateClient() {
        Client client = clientDao.createRandomClient();

        List<Client> clients = clientDao.getClients();

        Assertions.assertThat(clients)
                .as("Client is not added to the repository")
                .contains(client);

        Client changedClient = client.withFirstName(RandomUtils.getRandomAlphabetic(5))
                .withLastName(RandomUtils.getRandomAlphabetic(5))
                .withMiddleName(RandomUtils.getRandomAlphabetic(5));
        clientDao.updateClient(changedClient);
        Client updatedClient = clientDao.resolveClient(client.getClientId());

        List<Client> updatedClients = clientDao.getClients();

        Assertions.assertThat(updatedClients)
                .as("Client is not updated in the repository")
                .contains(changedClient);
    }

    @Test
    public void resolveUpdatedClient() {
        Client initialClient = clientDao.createRandomClient();

        List<Client> clients = clientDao.getClients();

        Assertions.assertThat(clients)
                .as("Client is not added to the repository")
                .contains(initialClient);

        Client changedClient = initialClient.withFirstName(RandomUtils.getRandomAlphabetic(5))
                .withLastName(RandomUtils.getRandomAlphabetic(5))
                .withMiddleName(RandomUtils.getRandomAlphabetic(5));
        clientDao.updateClient(changedClient);
        Client updatedClient = clientDao.resolveClient(initialClient.getClientId());

        Assertions.assertThat(updatedClient)
                .as("Client is not resolved as updated in the repository")
                .isEqualTo(changedClient);
    }

    @Test
    public void deleteClient() {
        Client client = clientDao.createRandomClient();

        List<Client> clients = clientDao.getClients();
        Assertions.assertThat(clients)
                .as("Client is not added to the repository")
                .contains(client);

        clientDao.deleteClient(client);
        Client deletedClient = clientDao.resolveClient(client.getClientId());

        List<Client> updatedClients = clientDao.getClients();

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
        Client client = clientDao.createRandomClientWithContactInformation2();

        List<Client> clients = clientDao.getClients();

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(clients)
                .as("Client is not added to the repository")
                .contains(client);

        List<ContactInformation> actualContacts = contactDao.getAllContacts();
        assertions.assertThat(actualContacts)
                .as("Contact information is not added to the repository")
                .containsAll(client.getContactInformation());

        assertions.assertAll();

        clientDao.deleteClient(client);

        List<Client> updatedClients = clientDao.getClients();
        List<ContactInformation> updatedContacts = contactDao.getAllContacts();

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
