package com.github.spb.tget.demo.repository.dbRepository

import com.github.spb.tget.demo.data.ClientEntity

class ClientDbRepository private constructor() : DbRepository<ClientEntity>() {

    init {
        this.entityName = "ClientEntity"
    }

    companion object {

        fun create(): ClientDbRepository {
            return ClientDbRepository()
        }
    }
}