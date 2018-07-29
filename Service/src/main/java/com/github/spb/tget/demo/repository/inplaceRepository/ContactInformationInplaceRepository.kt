package com.github.spb.tget.demo.repository.inplaceRepository

import com.github.spb.tget.demo.data.ContactEntity
import com.github.spb.tget.demo.util.RandomUtils

class ContactInformationInplaceRepository : InplaceRepository<ContactEntity> {

    companion object {
        @JvmStatic lateinit var contactsList : MutableList<ContactEntity>
    }

    init {
        contactsList = initContactList()
    }

    private fun initContactList(): MutableList<ContactEntity> {
        val contacts = mutableListOf(
                ContactEntity.random(), ContactEntity.random(), ContactEntity.random())
        contacts.forEach { contact -> contact.contactId = RandomUtils.randomInteger }
        return contacts
    }

    override fun getItems(): MutableList<ContactEntity> {
        return contactsList
    }

    override fun addItem(item: ContactEntity) {
        contactsList.add(item)
    }

    override fun addItemAndGetId(item: ContactEntity): Int {
        contactsList.add(item)
        return item.contactId
    }

    override fun updateItem(item: ContactEntity) {
        val contactToUpdate = contactsList.find { it -> it.contactId == item.contactId }
                ?: throw IllegalStateException("Contact with ID ${item.contactId} cannot be found")
        contactToUpdate.apply {
            email = item.email
            phone = item.phone
            address = item.address
        }
    }

    override fun deleteItem(item: ContactEntity) {
        val contactToDelete = contactsList.find { it -> it.contactId == item.contactId }
                ?: throw IllegalStateException("Contact with ID ${item.contactId} cannot be found")
        contactsList.remove(contactToDelete)
    }


}