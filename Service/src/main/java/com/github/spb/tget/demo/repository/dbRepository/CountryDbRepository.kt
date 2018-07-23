package com.github.spb.tget.demo.repository.dbRepository

import com.github.spb.tget.demo.data.Country

class CountryDbRepository private constructor() : DbRepository<Country>() {

    init {
        this.entityName = "Country"
    }

    companion object {

        fun create(): CountryDbRepository {
            return CountryDbRepository()
        }
    }
}
