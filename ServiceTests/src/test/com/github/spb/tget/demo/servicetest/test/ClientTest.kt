package com.github.spb.tget.demo.servicetest.test

import com.github.spb.tget.demo.servicetest.ClientServiceDao

import org.testng.annotations.Test

class ClientTest {

    @Test
    fun firstTest() {
        val dao = ClientServiceDao()

        dao.getClients().forEach { println(it.toString()) }
    }

}
