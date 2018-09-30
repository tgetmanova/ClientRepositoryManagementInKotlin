package com.github.spb.tget.demo.data

import com.github.spb.tget.demo.dto.AddressDto
import com.github.spb.tget.demo.dto.ContactDto
import com.github.spb.tget.demo.dto.PhoneDto
import com.github.spb.tget.demo.util.RandomUtils
import com.github.spb.tget.demo.util.getSubstringBetweenOrToEnd
import org.apache.commons.lang3.StringUtils

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

        fun fromDto(contactDto: ContactDto): ContactEntity {
            val contactInfo = ContactEntity()

            contactInfo.email = contactDto.emailAddress

            if (contactDto.phone != null) {
                var phone = String.format("+%d-%s",
                        contactDto.phone!!.countryCode,
                        contactDto.phone!!.phoneNumber)
                if (contactDto.phone!!.extension != null) {
                    phone += String.format(" ext. %d", contactDto.phone!!.extension)
                }
                contactInfo.phone = phone
            }

            if (contactDto.address != null) {
                val addressParts = ArrayList<String>()
                val addressDto = contactDto.address
                if (!StringUtils.isBlank(addressDto!!.addressLine)) {
                    addressParts.add(String.format("Street address: %s", addressDto.addressLine))
                }
                if (!StringUtils.isBlank(addressDto.postalCode)) {
                    addressParts.add(String.format("Postal code: %s", addressDto.postalCode))
                }
                if (!StringUtils.isBlank(addressDto.country)) {
                    addressParts.add(String.format("Country: %s", addressDto.country))
                }
                if (!StringUtils.isBlank(addressDto.state)) {
                    addressParts.add(String.format("State: %s", addressDto.state))
                }
                contactInfo.address = addressParts.joinToString("; ")
            }

            return contactInfo
        }
    }
}


fun ContactEntity.toDto(): ContactDto {
    val contactDto = ContactDto()
    contactDto.emailAddress = this.email

    val addressDto = AddressDto()
    addressDto.country = this.address?.getSubstringBetweenOrToEnd("Country: ", ";")
    addressDto.state = this.address?.getSubstringBetweenOrToEnd("State: ", ";")
    addressDto.addressLine = this.address?.getSubstringBetweenOrToEnd("Street address: ", ";")
    addressDto.postalCode = this.address?.getSubstringBetweenOrToEnd("Postal code: ", ";")

    val phoneDto = PhoneDto()
    phoneDto.countryCode = Integer.parseInt(
            StringUtils.substringBetween(this.phone, "+", "-"))
    if (this.phone?.contains("ext.") == true) {
        phoneDto.phoneNumber = StringUtils.substringBetween(phone, "-", "ext.").trim { it <= ' ' }
        phoneDto.extension = Integer.parseInt(StringUtils.substringAfter(phone, "ext.").trim { it <= ' ' })
    } else {
        phoneDto.phoneNumber = StringUtils.substringAfter(phone, "-")
    }

    return contactDto.apply {
        address = addressDto
        phone = phoneDto
    }
}