package com.github.spb.tget.demo.data

class EntityNotFoundException(entityIdentity: String) : Exception("Entity: $entityIdentity is not found")