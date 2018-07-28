package com.github.spb.tget.demo.test;

import com.github.spb.tget.demo.data.ClientEntity;
import com.github.spb.tget.demo.data.ContactInformation;
import com.github.spb.tget.demo.dao.ClientDao;
import com.github.spb.tget.demo.dao.ContactDao;

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
        ClientEntity client = clientDao.createRandomClient();

        List<ClientEntity> clients = clientDao.getClients();

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(clients)
                .as("ClientEntity is not added to the repository")
                .contains(client);

        assertions.assertAll();
    }

    @Test
    public void createClientWithSingleContactInfo() {
        ClientEntity client = ClientEntity.Companion.random().withRandomContactInformation();
        clientDao.createClient(client);

        List<ClientEntity> clients = clientDao.getClients();

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(clients)
                .as("ClientEntity is not added to the repository")
                .contains(client);

        List<ContactInformation> actualContacts = contactDao.getAllContacts();
        assertions.assertThat(actualContacts)
                .as("Contact information is not added to the repository")
                .containsAll(client.getContactInformation());

        assertions.assertAll();
    }

    @Test
    public void createClientWithMultipleContactInfo() {
        ClientEntity client = ClientEntity.Companion.random();
        Set<ContactInformation> expectedContacts = new HashSet<>();
        expectedContacts.add(ContactInformation.random());
        expectedContacts.add(ContactInformation.random());
        client.setContactInformation(expectedContacts);
        clientDao.createClient(client);

        List<ClientEntity> clients = clientDao.getClients();

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(clients)
                .as("ClientEntity is not added to the repository")
                .contains(client);

        List<ContactInformation> actualContacts = contactDao.getAllContacts();
        assertions.assertThat(actualContacts)
                .as("Contact information is not added to the repository")
                .containsAll(client.getContactInformation());

        assertions.assertAll();
    }

    @Test
    public void updateClient() {
        ClientEntity client = clientDao.createRandomClient();

        List<ClientEntity> clients = clientDao.getClients();

        Assertions.assertThat(clients)
                .as("ClientEntity is not added to the repository")
                .contains(client);

        ClientEntity changedClient = ClientEntity.Companion.random();
        changedClient.setClientId(client.getClientId());
        clientDao.updateClient(changedClient);

        List<ClientEntity> updatedClients = clientDao.getClients();
        Assertions.assertThat(updatedClients)
                .as("ClientEntity is not updated in the repository")
                .contains(changedClient);
    }

    @Test
    public void resolveUpdatedClient() {
        ClientEntity initialClient = clientDao.createRandomClient();

        List<ClientEntity> clients = clientDao.getClients();

        Assertions.assertThat(clients)
                .as("ClientEntity is not added to the repository")
                .contains(initialClient);

        ClientEntity changedClient = ClientEntity.Companion.random();
        changedClient.setClientId(initialClient.getClientId());
        clientDao.updateClient(changedClient);

        ClientEntity updatedClient = clientDao.resolveClient(initialClient.getClientId());
        Assertions.assertThat(updatedClient)
                .as("ClientEntity is not resolved as updated in the repository")
                .isEqualTo(changedClient);
    }

    @Test
    public void deleteClient() {
        ClientEntity client = clientDao.createRandomClient();

        List<ClientEntity> clients = clientDao.getClients();
        Assertions.assertThat(clients)
                .as("ClientEntity is not added to the repository")
                .contains(client);

        clientDao.deleteClient(client);
        ClientEntity deletedClient = clientDao.resolveClient(client.getClientId());

        List<ClientEntity> updatedClients = clientDao.getClients();

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(updatedClients)
                .as("ClientEntity must be deleted from the repository")
                .doesNotContain(deletedClient);
        assertions.assertThat(deletedClient)
                .as("Deleted ClientEntity should not be resolved in the repository")
                .isNull();
        assertions.assertAll();
    }

    @Test
    public void deleteClientWithContacts() {
        ClientEntity client = clientDao.createRandomClientWithContactInformation();

        List<ClientEntity> clients = clientDao.getClients();

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(clients)
                .as("ClientEntity is not added to the repository")
                .contains(client);

        List<ContactInformation> actualContacts = contactDao.getAllContacts();
        assertions.assertThat(actualContacts)
                .as("Contact information is not added to the repository")
                .containsAll(client.getContactInformation());

        assertions.assertAll();

        clientDao.deleteClient(client);

        List<ClientEntity> updatedClients = clientDao.getClients();
        List<ContactInformation> updatedContacts = contactDao.getAllContacts();

        assertions = new SoftAssertions();
        assertions.assertThat(updatedClients)
                .as("ClientEntity must be deleted from the repository")
                .doesNotContain(client);
        assertions.assertThat(updatedContacts)
                .as("Deleted ClientEntity contacts must be cleared from the repository")
                .doesNotContainAnyElementsOf(client.getContactInformation());
        assertions.assertAll();
    }
}
