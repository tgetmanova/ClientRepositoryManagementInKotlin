package com.github.spb.tget.demo.repository.inplaceRepository

import com.github.spb.tget.demo.data.ContactInformation
import com.github.spb.tget.demo.repository.InplaceRepository
import com.github.spb.tget.demo.util.RandomUtils

class ContactInformationInplaceRepository : InplaceRepository<ContactInformation> {

    private val contactsList = initContactList()

    private fun initContactList(): MutableList<ContactInformation> {
        val contacts = mutableListOf(
                ContactInformation.random(), ContactInformation.random(), ContactInformation.random())
        contacts.forEach { contact -> contact.contactId = RandomUtils.getRandomInteger() }
        return contacts
    }

    override fun getItems(): MutableList<ContactInformation> {
        return contactsList
    }

    override fun addItem(item: ContactInformation) {
        contactsList.add(item)
    }

    override fun addItemAndGetId(item: ContactInformation): Int {
        contactsList.add(item)
        return item.contactId
    }

    override fun updateItem(item: ContactInformation) {
        val contactToUpdate = contactsList.find { it -> it.contactId == item.contactId }
                ?: throw IllegalStateException("Contact with ID ${item.contactId} cannot be found")
        contactToUpdate.apply {
            email = item.email
            phone = item.phone
            address = item.address
        }
    }

    override fun deleteItem(item: ContactInformation) {
        val contactToDelete = contactsList.find { it -> it.contactId == item.contactId }
                ?: throw IllegalStateException("Contact with ID ${item.contactId} cannot be found")
        contactsList.remove(contactToDelete)
    }


}