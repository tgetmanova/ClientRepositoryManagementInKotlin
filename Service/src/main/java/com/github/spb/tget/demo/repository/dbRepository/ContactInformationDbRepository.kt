package com.github.spb.tget.demo.repository.dbRepository

import com.github.spb.tget.demo.data.ContactInformation

class ContactInformationDbRepository private constructor() : DbRepository<ContactInformation>() {

    init {
        this.entityName = "ContactInformation"
    }

    companion object {

        fun create(): ContactInformationDbRepository {
            return ContactInformationDbRepository()
        }
    }
}
