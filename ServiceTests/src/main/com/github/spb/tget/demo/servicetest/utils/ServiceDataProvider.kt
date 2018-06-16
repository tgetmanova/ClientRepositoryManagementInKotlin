package com.github.spb.tget.demo.servicetest.utils

import com.github.spb.tget.demo.servicetest.ClientServiceDao
import com.github.spb.tget.demo.servicetest.data.ClientDTO
import com.github.spb.tget.demo.servicetest.data.random

class ServiceDataProvider {

    companion object {
        fun getOrCreateClient(): ClientDTO {
            val dao = ClientServiceDao()
            val someExistingClient = dao.getClients().firstOrNull()
            if (someExistingClient != null) {
                return someExistingClient
            }
            return dao.createClient(ClientDTO().random())
        }
    }
}