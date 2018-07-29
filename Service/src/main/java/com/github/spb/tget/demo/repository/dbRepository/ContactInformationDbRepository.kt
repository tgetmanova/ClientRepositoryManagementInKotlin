package com.github.spb.tget.demo.repository.dbRepository

import com.github.spb.tget.demo.data.ContactEntity

class ContactInformationDbRepository private constructor() : DbRepository<ContactEntity>() {

    init {
        this.entityName = "ContactEntity"
    }

    companion object {

        fun create(): ContactInformationDbRepository {
            return ContactInformationDbRepository()
        }
    }
}
