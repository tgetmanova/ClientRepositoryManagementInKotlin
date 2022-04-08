package com.github.spb.tget.demo.repository

import com.github.spb.tget.demo.data.ClientEntity
import com.github.spb.tget.demo.data.ContactEntity

class InplaceDataInitializer {

    companion object {

        val clientsList: MutableList<ClientEntity> = mutableListOf(
            ClientEntity.random().withRandomContactInformation(),
            ClientEntity.random().withRandomContactInformation(),
            ClientEntity.random().withRandomContactInformation()
        )

        val contactsList: MutableList<ContactEntity> =
            clientsList.flatMap { it.contactInformation.orEmpty() }.toMutableList()
    }
}