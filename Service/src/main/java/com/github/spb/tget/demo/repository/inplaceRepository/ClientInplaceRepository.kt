package com.github.spb.tget.demo.repository.inplaceRepository

import com.github.spb.tget.demo.data.ClientEntity
import com.github.spb.tget.demo.repository.InplaceDataInitializer

class ClientInplaceRepository : InplaceRepository<ClientEntity> {

    companion object {
        @JvmStatic lateinit var clientsList: MutableList<ClientEntity>
    }

    init {
        clientsList = InplaceDataInitializer.clientsList
    }

    override fun getItems(): MutableList<ClientEntity> {
        return clientsList
    }

    override fun addItem(item: ClientEntity) {
        clientsList.add(item)
    }

    override fun addItemAndGetId(item: ClientEntity): Int {
        clientsList.add(item)
        return item.clientId
    }

    override fun updateItem(item: ClientEntity) {
        var clientToUpdate = clientsList.find { it -> it.clientId == item.clientId }
                ?: throw IllegalStateException("ClientEntity with ID ${item.clientId} cannot be found")
        clientToUpdate.apply {
            firstName = item.firstName
            lastName = item.lastName
            middleName = item.middleName
            dateOfBirth = item.dateOfBirth
            contactInformation = item.contactInformation
        }
    }

    override fun deleteItem(item: ClientEntity) {
        val clientToDelete = clientsList.find { it -> it.clientId == item.clientId }
                ?: throw IllegalStateException("ClientEntity with ID ${item.clientId} cannot be found")
        clientsList.remove(clientToDelete)
    }

}