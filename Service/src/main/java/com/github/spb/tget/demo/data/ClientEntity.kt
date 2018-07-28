package com.github.spb.tget.demo.data

import com.github.spb.tget.demo.util.RandomUtils

import java.sql.Date

import java.util.HashSet

class ClientEntity {

    var clientId: Int = 0
    var firstName: String? = null
    var lastName: String? = null
    var middleName: String? = null
    var dateOfBirth: Date? = null
    var contactInformation: MutableSet<ContactInformation>? = null

    fun withRandomContactInformation(): ClientEntity {
        this.contactInformation = HashSet()
        val contactInfo = ContactInformation.random()
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
    }
}
