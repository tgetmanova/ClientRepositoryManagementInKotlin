package com.github.spb.tget.demo.dao

import com.github.spb.tget.demo.data.ClientEntity
import com.github.spb.tget.demo.data.ContactEntity
import com.github.spb.tget.demo.repository.Repository
import com.github.spb.tget.demo.repository.RepositoryFactory
import com.github.spb.tget.demo.repository.inplaceRepository.InplaceRepository

import java.util.HashSet
import java.util.UUID

class ClientDao {

    private val clientRepository: Repository<ClientEntity>
    private val contactInformationRepository: Repository<ContactEntity>
    private val repoType = System.getProperty("repoType")

    init {
        clientRepository = RepositoryFactory.getClientRepositoryByType(repoType)
        contactInformationRepository = RepositoryFactory.getContactInformationRepositoryByType(repoType)
    }

    fun getClients(): List<ClientEntity> {
        return this.clientRepository.getItems()
    }

    fun createRandomClient(): ClientEntity {
        val client = ClientEntity.random()
        this.clientRepository.addItem(client)
        return client
    }

    fun createRandomClientWithContactInformation(): ClientEntity {
        val client = ClientEntity.random()
        val conInfo = HashSet<ContactEntity>()
        conInfo.add(ContactEntity.random())
        client.contactInformation = conInfo

        this.clientRepository.addItem(client)

        conInfo.forEach { ci ->
            ci.client = client
            this.contactInformationRepository.addItem(ci)
        }

        return client
    }

    fun createClient(client: ClientEntity): ClientEntity {
        client.internalId = UUID.randomUUID()
        val clientId = this.clientRepository.addItemAndGetId(client)
        client.clientId = clientId

        client.contactInformation?.forEach { conInfo ->
            conInfo.client = client
            this.contactInformationRepository.addItem(conInfo)
        }

        return client
    }

    fun deleteClient(client: ClientEntity) {
        this.clientRepository.deleteItem(client)
        if (this.clientRepository is InplaceRepository) {
            client.contactInformation?.forEach { contact -> this.contactInformationRepository.deleteItem(contact) }
        }
    }

    fun resolveClient(clientId: Int): ClientEntity? {
        return getClients().find { client -> client.clientId == clientId }
    }

    fun updateClient(updatedClient: ClientEntity) {
        this.clientRepository.updateItem(updatedClient)
    }
}