package com.github.spb.tget.demo.servicetest

import com.github.spb.tget.demo.servicetest.data.ClientDTO
import com.github.spb.tget.demo.servicetest.utils.whenDoRequest
import com.github.spb.tget.demo.servicetest.utils.Serializer

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.response.Response

class ClientServiceDao {

    companion object {
        const val CLIENTS_ENDPOINT = "/clients"
        const val CLIENT_ENDPOINT = CLIENTS_ENDPOINT + "/{id}"
    }

    init {
        RestAssured.port = 8080
        RestAssured.baseURI = "http://localhost"
    }

    fun getClients(): List<ClientDTO> {
        val response =
                given()
                .whenDoRequest()
                    .get(CLIENTS_ENDPOINT)
                .thenReturn()

        return Serializer.readResponse(response)
    }

    fun getClient(clientId: Int): Response {
        return given()
                .whenDoRequest()
                    .get(CLIENT_ENDPOINT, clientId)
                .thenReturn()
    }

    fun createClient(client: ClientDTO): ClientDTO {
        val response =
                given()
                    .body(Serializer.writeValueAsString(client))
                    .contentType(ContentType.JSON)
                .whenDoRequest()
                    .post(CLIENTS_ENDPOINT)
                .thenReturn()

        return Serializer.readResponse(response)
    }

    fun deleteClient(id: Int?) {
        given()
            .whenDoRequest()
                .delete(CLIENT_ENDPOINT, id)
            .thenReturn()
    }
}