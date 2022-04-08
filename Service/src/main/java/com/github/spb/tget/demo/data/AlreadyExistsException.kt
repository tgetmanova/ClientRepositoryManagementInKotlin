package com.github.spb.tget.demo.data

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.CONFLICT)
class AlreadyExistsException(entityIdentity: String) : Exception("Entity: $entityIdentity already exists")