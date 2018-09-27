package com.github.spb.tget.demo

import com.github.spb.tget.demo.converter.ClientConverter
import com.github.spb.tget.demo.dao.ClientDao
import com.github.spb.tget.demo.dao.ContactDao
import com.github.spb.tget.demo.data.EntityNotFoundException
import com.github.spb.tget.demo.dto.ClientDto
import com.github.spb.tget.demo.dto.ContactDto

class ClientManager {

    private val clientDao = ClientDao()
    private val contactDao = ContactDao()
    private val clientConverter = ClientConverter()

    fun getClients(): List<ClientDto> {
        return clientDao.getClients().map { it -> clientConverter.toDto(it) }
    }

    fun getClient(id: Int): ClientDto {
        val targetClient = clientDao.resolveClient(id) ?: throw EntityNotFoundException("Client ID $id")
        return clientConverter.toDto(targetClient)
    }

    fun getClientContacts(id: Int): List<ContactDto>? {
        return clientConverter.toDto(clientDao.resolveClient(id)).contacts
    }

    fun createClient(client: ClientDto): ClientDto {
        return clientConverter.toDto(clientDao.createClient(clientConverter.fromDto(client)))
    }

    fun addContacts(contacts: List<ContactDto>, id: Int) {
        val client = clientDao.resolveClient(id) ?: throw EntityNotFoundException("Client ID $id")
        contactDao.addContacts(contacts.map { it -> clientConverter.contactInfoFromDto(it) }, client)
    }

    fun deleteClient(id: Int) {
        val client = clientDao.resolveClient(id) ?: throw EntityNotFoundException("Client ID $id")
        clientDao.deleteClient(client)
    }

    fun updateClient(id: Int, clientDto: ClientDto) {
        clientDao.resolveClient(id) ?: throw EntityNotFoundException("Client ID $id")
        clientDto.id = id
        clientDao.updateClient(clientConverter.fromDto(clientDto))
    }
}