package com.github.spb.tget.demo.servicetest.utils

import io.restassured.specification.RequestSpecification

fun RequestSpecification.whenDoRequest(): RequestSpecification {
    return this.`when`()
}
