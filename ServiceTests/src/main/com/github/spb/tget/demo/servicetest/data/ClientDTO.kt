package com.github.spb.tget.demo.servicetest.data

import com.github.spb.tget.demo.servicetest.utils.RandomUtil

import java.time.LocalDateTime

data class ClientDTO(
        var id: Int?,
        var firstName: String?,
        var lastName: String?,
        var middleName: String?,
        var dateOfBirth: LocalDateTime?,
        var contacts: List<ContactDTO>?) {

    constructor() : this(null, null, null, null, null, null)

    override fun toString(): String {
        return "Client [$id]: first name [$firstName], last name [$lastName], " +
                "middle name: [$middleName], date of birth: [$dateOfBirth], "
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        if (other === this) {
            return true
        }
        if (other !is ClientDTO) {
            return false
        }

        return if ((this.id != null && this.id!! > 0) && (other.id != null && other.id!! > 0)) {
            this.id == other.id
        } else (if (this.firstName != null) this.firstName.equals(other.firstName) else true)
                && (if (this.lastName != null) this.lastName.equals(other.lastName) else true)
                && (if (this.middleName != null) this.middleName.equals(other.middleName) else true)
    }
}

fun ClientDTO.random(): ClientDTO {
    this.dateOfBirth = LocalDateTime.now()
    this.firstName = RandomUtil.getRandomAlphabetic()
    this.lastName = RandomUtil.getRandomAlphabetic()
    this.middleName = RandomUtil.getRandomAlphabetic()
    return this
}