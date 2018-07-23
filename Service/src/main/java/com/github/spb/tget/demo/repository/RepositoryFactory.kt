package com.github.spb.tget.demo.repository

import com.github.spb.tget.demo.repository.dbRepository.ClientDbRepository
import com.github.spb.tget.demo.repository.dbRepository.ContactInformationDbRepository
import com.github.spb.tget.demo.repository.inplaceRepository.ClientInplaceRepository
import com.github.spb.tget.demo.repository.inplaceRepository.ContactInformationInplaceRepository

class RepositoryFactory {

    companion object {

        fun getClientRepositoryByType(repoType: String) = when (repoType) {
            "db" -> ClientDbRepository.create()
            "inplace" -> ClientInplaceRepository()
            else -> throw IllegalArgumentException("Unknown repository type: " + repoType)
        }

        fun getContactInformationRepositoryByType(repoType: String) = when (repoType) {
            "db" -> ContactInformationDbRepository.create()
            "inplace" -> ContactInformationInplaceRepository()
            else -> throw IllegalArgumentException("Unknown repository type: " + repoType)
        }
    }
}