package com.github.spb.tget.demo.controller

import com.github.spb.tget.demo.ClientManager
import com.github.spb.tget.demo.data.InvalidInputException
import com.github.spb.tget.demo.dto.ContactDto

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
class ContactController {

    private val clientManager = ClientManager()

    @RequestMapping(CLIENT_CONTACTS_ENDPOINT, method = [(RequestMethod.GET)])
    fun getClientContacts(@PathVariable id: Int?): List<ContactDto>? {
        id ?: throw InvalidInputException("ClientID")
        if (id <= 0) throw InvalidInputException("ClientID")
        return clientManager.getClientContacts(id )
    }

    @RequestMapping(CLIENT_CONTACTS_ENDPOINT, method = [(RequestMethod.POST)])
    fun addContacts(@RequestBody contacts: List<ContactDto>, @PathVariable id: Int?) {
        clientManager.addContacts(contacts, id ?: throw InvalidInputException("ClientID"))
    }
}
