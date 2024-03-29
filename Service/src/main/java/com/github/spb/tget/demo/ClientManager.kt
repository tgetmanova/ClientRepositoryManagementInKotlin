package com.github.spb.tget.demo

import com.github.spb.tget.demo.dao.ClientDao
import com.github.spb.tget.demo.dao.ContactDao
import com.github.spb.tget.demo.data.AlreadyExistsException
import com.github.spb.tget.demo.data.ClientEntity
import com.github.spb.tget.demo.data.ContactEntity
import com.github.spb.tget.demo.data.EntityNotFoundException
import com.github.spb.tget.demo.data.toDto
import com.github.spb.tget.demo.dto.ClientDto
import com.github.spb.tget.demo.dto.ContactDto

class ClientManager {

    private val clientDao = ClientDao()
    private val contactDao = ContactDao()

    fun getClients(): List<ClientDto> {
        return clientDao.getClients().map { it -> it.toDto() }
    }

    fun getClient(id: Int): ClientDto {
        val targetClient = clientDao.resolveClient(id) ?: throw EntityNotFoundException("Client ID $id")
        return targetClient.toDto()
    }

    fun getClientContacts(id: Int): List<ContactDto>? {
        val client = clientDao.resolveClient(id)
        client ?: throw EntityNotFoundException("Client with ID $id")
        return clientDao.resolveClient(id)?.toDto()?.contacts
    }

    fun createClient(client: ClientDto): ClientDto {
        if (clientDao.getClients().firstOrNull { it.clientId == client.id } != null) {
            throw AlreadyExistsException("Client with Id ${client.id} already exists")
        }
        return clientDao.createClient(ClientEntity.fromDto(client)).toDto()
    }

    fun addContacts(contacts: List<ContactDto>, id: Int) {
        val client = clientDao.resolveClient(id) ?: throw EntityNotFoundException("Client ID $id")
        if (contacts.any { contact ->
            contactDao.getAllContacts().any { it.email?.equals(contact.emailAddress, ignoreCase = true) == true }
        }) {
            throw AlreadyExistsException("")
        }

        contactDao.addContacts(contacts.map { it -> ContactEntity.fromDto(it) }, client)
        client.contactInformation?.addAll(contacts.map { it -> ContactEntity.fromDto(it) });
        clientDao.updateClient(client)
    }

    fun deleteClient(id: Int) {
        val client = clientDao.resolveClient(id) ?: throw EntityNotFoundException("Client ID $id")
        clientDao.deleteClient(client)
    }

    fun updateClient(id: Int, clientDto: ClientDto) {
        clientDao.resolveClient(id) ?: throw EntityNotFoundException("Client ID $id")
        clientDto.id = id
        clientDao.updateClient(ClientEntity.fromDto(clientDto))
    }
}