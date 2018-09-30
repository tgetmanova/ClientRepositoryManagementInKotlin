package com.github.spb.tget.demo.controller

import com.github.spb.tget.demo.ClientManager
import com.github.spb.tget.demo.data.InvalidInputException
import com.github.spb.tget.demo.dto.ClientDto

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class ClientController {

    private val clientManager = ClientManager()

    @RequestMapping(CLIENTS_ENDPOINT, method = [(RequestMethod.GET)])
    fun getClients(): List<ClientDto> {
        return clientManager.getClients()
    }

    @RequestMapping("$CLIENTS_ENDPOINT/{id}", method = [(RequestMethod.GET)])
    fun getClient(@PathVariable id: Int?): ClientDto {
        return clientManager.getClient(id ?: throw InvalidInputException("ClientID"))
    }

    @RequestMapping(CLIENTS_ENDPOINT, method = [(RequestMethod.POST)])
    fun createClient(@RequestBody client: ClientDto): ClientDto {
        return clientManager.createClient(client)
    }

    @RequestMapping("$CLIENTS_ENDPOINT/{id}", method = [(RequestMethod.DELETE)])
    fun deleteClient(@PathVariable id: Int?) {
        clientManager.deleteClient(id ?: throw InvalidInputException("ClientID"))
    }

    @RequestMapping("$CLIENTS_ENDPOINT/{id}", method = [(RequestMethod.PUT)])
    fun updateClient(@PathVariable id: Int?, @RequestBody client: ClientDto) {
        clientManager.updateClient(id ?: throw InvalidInputException("ClientID"), client)
    }

}