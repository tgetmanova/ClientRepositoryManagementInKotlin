package com.github.spb.tget.demo.repository.dbRepository

import com.github.spb.tget.demo.data.CountryEntity

class CountryDbRepository private constructor() : DbRepository<CountryEntity>() {

    init {
        this.entityName = "CountryEntity"
    }

    companion object {

        fun create(): CountryDbRepository {
            return CountryDbRepository()
        }
    }
}
