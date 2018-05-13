package com.github.spb.tget.demo.servicetest.utils

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue

import io.restassured.response.Response

class Serializer {

    companion object {

        fun getObjectMapper(): ObjectMapper {
            val mapper = ObjectMapper()
                    .registerModule(KotlinModule())
                    .registerModule(JavaTimeModule())
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            return mapper
        }

        inline fun <reified T : Any> readResponse(response: Response): T {
            return getObjectMapper().readValue(response.asString())
        }
    }


}