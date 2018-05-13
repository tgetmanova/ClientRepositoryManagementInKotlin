package com.github.spb.tget.demo.servicetest.test

import com.github.spb.tget.demo.servicetest.ClientServiceDao
import com.github.spb.tget.demo.servicetest.data.ClientDTO
import com.github.spb.tget.demo.servicetest.data.random

import org.testng.Assert

import org.testng.annotations.Test

class ClientTest() {

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

}
