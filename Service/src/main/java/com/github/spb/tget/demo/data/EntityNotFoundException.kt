package com.github.spb.tget.demo.data

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class EntityNotFoundException(entityIdentity: String) : Exception("Entity: $entityIdentity is not found")