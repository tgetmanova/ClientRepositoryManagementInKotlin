package com.github.spb.tget.demo.servicetest.data

import java.time.LocalDateTime

data class ClientDTO (
        val id: Int,
        val firstName: String?,
        val lastName: String?,
        val middleName: String?,
        val dateOfBirth: LocalDateTime,
        val contacts: List<ContactDTO>) {

    override fun toString(): String {
        return "Client [$id]: first name [$firstName], last name [$lastName], " +
                "middle name: [$middleName], date of birth: [$dateOfBirth], "
    }
}