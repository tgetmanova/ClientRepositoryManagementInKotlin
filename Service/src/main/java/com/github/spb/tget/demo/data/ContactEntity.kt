package com.github.spb.tget.demo.data

import com.github.spb.tget.demo.util.RandomUtils

class ContactEntity {

    var client: ClientEntity? = null
    var contactId: Int = 0
    var phone: String? = null
    var address: String? = null
    var email: String? = null

    override fun toString(): String {
        return "ContactID: [$contactId],  Address: $address, Phone: $phone, Email: $email"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        if (other === this) {
            return true
        }
        if (other !is ContactEntity) {
            return false
        }

        return if (this.contactId > 0 && other.contactId > 0) {
            this.contactId == other.contactId
        } else this.address != null && this.address == other.address
                && this.phone != null && this.phone == other.phone
                && this.email != null && this.email == other.email
    }

    companion object {
        fun random(): ContactEntity {
            return ContactEntity().apply {
                address = "Country: ${RandomUtils.getRandomAlphabetic(10)}; " +
                        "State: ${RandomUtils.getRandomAlphabetic(10)}; " +
                        "Street address: ${RandomUtils.getRandomString(60)}; " +
                        "Postal code: ${RandomUtils.getRandomString(20)}"
                email = RandomUtils.randomEmailAddress
                phone = "+${RandomUtils.getRandomNumeric(3)}" +
                        "-${RandomUtils.getRandomNumeric(10)}" +
                        " ext.${RandomUtils.getRandomNumeric(5)}"
            }
        }
    }
}