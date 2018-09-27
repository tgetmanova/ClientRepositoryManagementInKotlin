package com.github.spb.tget.demo.dao

import com.github.spb.tget.demo.data.ClientEntity
import com.github.spb.tget.demo.data.ContactEntity
import com.github.spb.tget.demo.repository.Repository
import com.github.spb.tget.demo.repository.RepositoryFactory

class ContactDao {

    private val contactInformationRepository: Repository<ContactEntity>
    private val repoType = System.getProperty("repoType")

    init {
        contactInformationRepository = RepositoryFactory.getContactInformationRepositoryByType(repoType)
    }

    fun addContacts(contacts: List<ContactEntity>, client: ClientEntity) {
        contacts.forEach { c ->
            c.client = client
            contactInformationRepository.addItem(c)
        }
    }

    fun getAllContacts(): List<ContactEntity> {
        return contactInformationRepository.getItems()
    }
}
