package com.github.spb.tget.demo.servicetest.test

import com.github.spb.tget.demo.servicetest.utils.whenDoRequest

import org.testng.annotations.Test

import io.restassured.RestAssured
import io.restassured.RestAssured.given

class ClientTest {

    init {
        RestAssured.port = 8080
        RestAssured.baseURI = "http://localhost"
    }

    @Test
    fun firstTest() {
        val resp = given()
                .whenDoRequest()
                .get("/clients")
                .thenReturn()

        println(resp.asString())
    }

}
