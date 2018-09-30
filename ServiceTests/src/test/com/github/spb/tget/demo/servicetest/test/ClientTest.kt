package com.github.spb.tget.demo.servicetest.test

import com.github.spb.tget.demo.servicetest.ClientServiceDao
import com.github.spb.tget.demo.servicetest.data.ClientDTO
import com.github.spb.tget.demo.servicetest.data.random
import com.github.spb.tget.demo.servicetest.utils.RandomUtil
import com.github.spb.tget.demo.servicetest.utils.ServiceDataProvider

import org.testng.Assert
import org.testng.annotations.Test

class ClientTest {

    private val dao = ClientServiceDao()

    @Test
    fun createClientShouldReturnNewlyAddedClient() {
        val expectedClient = ClientDTO().random()

        val createdClient = dao.createClient(expectedClient)

        Assert.assertEquals(expectedClient, createdClient, "Data retrieved for newly created client is incorrect")
    }

    @Test
    fun createClientShouldAddNewClientToRepository() {
        val client = ClientDTO().random()

        dao.createClient(client)

        val foundClient = dao.getClients().find { c -> c == client }
        Assert.assertNotNull(foundClient, "Failed to retrieve newly created client")
    }

    @Test
    fun deleteClientShouldDeleteClientFromRepository() {
        val client = ServiceDataProvider.getOrCreateClient()

        dao.deleteClient(client.id)

        val foundClient = dao.getClients().find { c -> c == client }
        Assert.assertNull(foundClient, "After deletion, client still can be retrieved")
    }

    @Test
    fun resolveNonExistingClientMustThrowEntityNotFoundError() {
        val randomId = RandomUtil.randomInteger;
        val response = dao.getClient(randomId)
        Assert.assertTrue(response.asString().contains("Entity: Client ID $randomId is not found"))
    }

}
