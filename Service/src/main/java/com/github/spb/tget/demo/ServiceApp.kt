package com.github.spb.tget.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ServiceApp

fun main(args: Array<String>) {
    SpringApplication.run(ServiceApp::class.java, *args)
}


