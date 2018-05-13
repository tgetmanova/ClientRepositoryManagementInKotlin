package com.github.spb.tget.demo.servicetest

import com.github.spb.tget.demo.servicetest.data.ClientDTO
import com.github.spb.tget.demo.servicetest.utils.whenDoRequest
import com.github.spb.tget.demo.servicetest.utils.Serializer

import io.restassured.RestAssured

class ClientServiceDao {

    init {
        RestAssured.port = 8080
        RestAssured.baseURI = "http://localhost"
    }

    fun getClients(): List<ClientDTO> {
        val resp = RestAssured.given()
                .whenDoRequest()
                    .get("/clients")
                .thenReturn()

        return Serializer.readResponse(resp)
    }
}