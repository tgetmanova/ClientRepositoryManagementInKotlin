package com.github.spb.tget.demo.data

import com.github.spb.tget.demo.dto.ClientDto
import com.github.spb.tget.demo.dto.ContactDto
import com.github.spb.tget.demo.util.RandomUtils

import org.apache.commons.lang3.StringUtils

import java.sql.Date
import java.util.ArrayList
import java.util.HashSet

class ClientEntity {

    var clientId: Int = 0
    var firstName: String? = null
    var lastName: String? = null
    var middleName: String? = null
    var dateOfBirth: Date? = null
    var contactInformation: MutableSet<ContactEntity>? = null

    fun withRandomContactInformation(): ClientEntity {
        this.contactInformation = HashSet()
        val contactInfo = ContactEntity.random()
        this.contactInformation!!.add(contactInfo)
        contactInfo.client = this
        return this
    }

    override fun toString(): String {
        return "ClientID: [$clientId]: $firstName $lastName"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        if (other === this) {
            return true
        }
        if (other !is ClientEntity) {
            return false
        }

        return if (this.clientId > 0 && other.clientId > 0) {
            this.clientId == other.clientId
        } else this.firstName != null && this.firstName == other.firstName
                && this.lastName != null && this.lastName == other.lastName
                && this.middleName != null && this.middleName == other.middleName
    }

    companion object {

        fun random(): ClientEntity {
            return ClientEntity().apply {
                firstName = RandomUtils.getRandomAlphabetic(15)
                lastName = RandomUtils.getRandomAlphabetic(15)
                middleName = RandomUtils.getRandomAlphabetic(15)
                dateOfBirth = Date.valueOf(RandomUtils.randomDateOfBirthAsAdult().toLocalDate())
            }
        }

        fun fromDto(clientDto: ClientDto): ClientEntity {
            val client = ClientEntity()
            client.clientId = clientDto.id
            client.firstName = clientDto.firstName
            client.lastName = clientDto.lastName
            client.middleName = clientDto.middleName
            client.dateOfBirth = Date.valueOf(clientDto.dateOfBirth!!.toLocalDate().toString())

            val contactInfoSet = HashSet<ContactEntity>()
            if (clientDto.contacts != null && !clientDto.contacts!!.isEmpty()) {
                clientDto.contacts?.forEach { contact ->
                    val contactInfo = ContactEntity.fromDto(contact)
                    if (!StringUtils.isAllBlank(contactInfo.address,
                                    contactInfo.email, contactInfo.phone)) {
                        contactInfoSet.add(contactInfo)
                    }
                }
            }
            client.contactInformation = contactInfoSet

            return client
        }
    }
}

fun ClientEntity.toDto(): ClientDto {
    val clientDto = ClientDto()

    clientDto.id = this.clientId
    clientDto.firstName = this.firstName
    clientDto.lastName = this.lastName
    clientDto.middleName = this.middleName
    clientDto.dateOfBirth = if (this.dateOfBirth == null)
        null
    else
        this.dateOfBirth?.toLocalDate()?.atStartOfDay()

    val contactDtos = ArrayList<ContactDto>()

    this.contactInformation?.forEach { ci ->
        contactDtos.add(ci.toDto())
    }

    clientDto.contacts = contactDtos
    return clientDto
}
