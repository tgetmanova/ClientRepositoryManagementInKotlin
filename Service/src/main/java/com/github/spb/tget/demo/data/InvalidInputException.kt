package com.github.spb.tget.demo.data

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class InvalidInputException(parameterName: String) : Exception("Invalid value for parameter: [$parameterName]")