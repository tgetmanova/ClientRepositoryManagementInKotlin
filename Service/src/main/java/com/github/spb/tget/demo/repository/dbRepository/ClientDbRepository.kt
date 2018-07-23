package com.github.spb.tget.demo.repository.dbRepository

import com.github.spb.tget.demo.data.Client

class ClientDbRepository private constructor() : DbRepository<Client>() {

    init {
        this.entityName = "Client"
    }

    companion object {

        fun create(): ClientDbRepository {
            return ClientDbRepository()
        }
    }
}