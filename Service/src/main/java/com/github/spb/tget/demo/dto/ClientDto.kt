package com.github.spb.tget.demo.dto

import java.time.LocalDateTime

class ClientDto {

    var id: Int = 0
    var firstName: String? = null
    var lastName: String? = null
    var middleName: String? = null
    var dateOfBirth: LocalDateTime? = null
    var contacts: List<ContactDto>? = null
}