package com.github.spb.tget.demo.repository.inplaceRepository

import com.github.spb.tget.demo.data.Client
import com.github.spb.tget.demo.repository.InplaceRepository
import com.github.spb.tget.demo.util.RandomUtils

class ClientInplaceRepository : InplaceRepository<Client> {

    companion object {
        @JvmStatic lateinit var clientsList: MutableList<Client>
    }

    init {
        clientsList = initClientsList()
    }

    private fun initClientsList(): MutableList<Client> {
        val clients = mutableListOf(
                Client.random().withRandomContactInformation(),
                Client.random().withRandomContactInformation(),
                Client.random().withRandomContactInformation())
        clients.forEach { client -> client.clientId = RandomUtils.getRandomInteger() }
        return clients
    }

    override fun getItems(): MutableList<Client> {
        return clientsList
    }

    override fun addItem(item: Client) {
        clientsList.add(item)
    }

    override fun addItemAndGetId(item: Client): Int {
        clientsList.add(item)
        return item.clientId
    }

    override fun updateItem(item: Client) {
        var clientToUpdate = clientsList.find { it -> it.clientId == item.clientId }
                ?: throw IllegalStateException("Client with ID ${item.clientId} cannot be found")
        clientToUpdate.apply {
            firstName = item.firstName
            lastName = item.lastName
            middleName = item.middleName
            dateOfBirth = item.dateOfBirth
            contactInformation = item.contactInformation
        }
    }

    override fun deleteItem(item: Client) {
        val clientToDelete = clientsList.find { it -> it.clientId == item.clientId }
                ?: throw IllegalStateException("Client with ID ${item.clientId} cannot be found")
        clientsList.remove(clientToDelete)
    }

}