package com.github.spb.tget.demo.repository

interface Repository<T> {

    fun getItems(): List<T>

    fun addItem(item: T)

    fun addItemAndGetId(item: T): Int

    fun updateItem(item: T)

    fun deleteItem(item: T)
}