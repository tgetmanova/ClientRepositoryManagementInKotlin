package com.github.spb.tget.demo.test

import com.github.spb.tget.demo.dao.ClientDao
import com.github.spb.tget.demo.dao.ContactDao
import com.github.spb.tget.demo.data.ClientEntity
import com.github.spb.tget.demo.data.ContactEntity

import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions

import org.junit.Test

import java.util.HashSet

class ClientDaoTests {

    private val clientDao: ClientDao = ClientDao()
    private val contactDao: ContactDao = ContactDao()

    @Test
    fun createClient() {
        val client = clientDao.createRandomClient()

        val clients = clientDao.getClients()

        val assertions = SoftAssertions()
        assertions.assertThat(clients)
                .withFailMessage("ClientEntity is not added to the repository")
                .contains(client)
        assertions.assertAll()
    }

    @Test
    fun createClientWithSingleContactInfo() {
        val client = ClientEntity.random().withRandomContactInformation()
        clientDao.createClient(client)

        val clients = clientDao.getClients()

        val assertions = SoftAssertions()
        assertions.assertThat(clients)
                .withFailMessage("ClientEntity is not added to the repository")
                .contains(client)

        val actualContacts = contactDao.getAllContacts()
        assertions.assertThat(actualContacts)
                .withFailMessage("Contact information is not added to the repository")
                .containsAll(client.contactInformation)

        assertions.assertAll()
    }

    @Test
    fun createClientWithMultipleContactInfo() {
        val client = ClientEntity.random()
        val expectedContacts = HashSet<ContactEntity>()
        expectedContacts.add(ContactEntity.random())
        expectedContacts.add(ContactEntity.random())
        client.contactInformation = expectedContacts
        clientDao.createClient(client)

        val clients = clientDao.getClients()

        val assertions = SoftAssertions()
        assertions.assertThat(clients)
                .withFailMessage("ClientEntity is not added to the repository")
                .contains(client)

        val actualContacts = contactDao.getAllContacts()
        assertions.assertThat(actualContacts)
                .withFailMessage("Contact information is not added to the repository")
                .containsAll(client.contactInformation)

        assertions.assertAll()
    }

    @Test
    fun updateClient() {
        val client = clientDao.createRandomClient()

        val clients = clientDao.getClients()

        Assertions.assertThat(clients)
                .withFailMessage("ClientEntity is not added to the repository")
                .contains(client)

        val changedClient = ClientEntity.random()
        changedClient.clientId = client.clientId
        clientDao.updateClient(changedClient)

        val updatedClients = clientDao.getClients()
        Assertions.assertThat(updatedClients)
                .withFailMessage("ClientEntity is not updated in the repository")
                .contains(changedClient)
    }

    @Test
    fun resolveUpdatedClient() {
        val initialClient = clientDao.createRandomClient()

        val clients = clientDao.getClients()

        Assertions.assertThat(clients)
                .withFailMessage("ClientEntity is not added to the repository")
                .contains(initialClient)

        val changedClient = ClientEntity.random()
        changedClient.clientId = initialClient.clientId
        clientDao.updateClient(changedClient)

        val updatedClient = clientDao.resolveClient(initialClient.clientId)
        Assertions.assertThat(updatedClient)
                .withFailMessage("ClientEntity is not resolved as updated in the repository")
                .isEqualTo(changedClient)
    }

    @Test
    fun deleteClient() {
        val client = clientDao.createRandomClient()

        val clients = clientDao.getClients()
        Assertions.assertThat(clients)
                .withFailMessage("ClientEntity is not added to the repository")
                .contains(client)

        clientDao.deleteClient(client)
        val deletedClient = clientDao.resolveClient(client.clientId)

        val updatedClients = clientDao.getClients()

        val assertions = SoftAssertions()
        assertions.assertThat(updatedClients)
                .withFailMessage("ClientEntity must be deleted from the repository")
                .doesNotContain(deletedClient)
        assertions.assertThat(deletedClient)
                .withFailMessage("Deleted ClientEntity should not be resolved in the repository")
                .isNull()
        assertions.assertAll()
    }

    @Test
    fun deleteClientWithContacts() {
        val client = clientDao.createRandomClientWithContactInformation()

        val clients = clientDao.getClients()

        var assertions = SoftAssertions()
        assertions.assertThat(clients)
                .withFailMessage("ClientEntity is not added to the repository")
                .contains(client)
        val actualContacts = contactDao.getAllContacts()
        assertions.assertThat(actualContacts)
                .withFailMessage("Contact information is not added to the repository")
                .containsAll(client.contactInformation)
        assertions.assertAll()

        clientDao.deleteClient(client)

        val updatedClients = clientDao.getClients()
        val updatedContacts = contactDao.getAllContacts()

        assertions = SoftAssertions()
        assertions.assertThat(updatedClients)
                .withFailMessage("ClientEntity must be deleted from the repository")
                .doesNotContain(client)
        assertions.assertThat(updatedContacts)
                .withFailMessage("Deleted ClientEntity contacts must be cleared from the repository")
                .doesNotContainAnyElementsOf(client.contactInformation)
        assertions.assertAll()
    }
}