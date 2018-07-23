package com.github.spb.tget.demo.repository.dbRepository

import com.github.spb.tget.demo.repository.Repository
import com.github.spb.tget.demo.util.DbSessionUtil

open class DbRepository<T> : Repository<T> {

    protected open var entityName: String? = null

    override fun getItems(): List<T> {
        val items: List<T>
        try {
            val session = DbSessionUtil.getSessionFactory().currentSession
            val transaction = session.beginTransaction()
            val query = session.createQuery("from " + entityName!!)

            items = query.list() as List<T>

            transaction.commit()
        } finally {
            DbSessionUtil.closeSession()
        }

        return items
    }

    override fun addItem(item: T) {
        val session = DbSessionUtil.getSessionFactory().currentSession
        val transaction = session.beginTransaction()
        try {
            session.save(item)
            transaction!!.commit()
        } catch (exception: Throwable) {
            transaction?.rollback()
            throw exception
        } finally {
            DbSessionUtil.closeSession()
        }
    }

    override fun addItemAndGetId(item: T): Int {
        val session = DbSessionUtil.getSessionFactory().currentSession
        val transaction = session.beginTransaction()
        var id = -1
        try {
            id = session.save(item) as Int
            transaction!!.commit()
        } catch (exception: Throwable) {
            transaction?.rollback()
            throw exception
        } finally {
            DbSessionUtil.closeSession()
        }
        return id
    }

    override fun updateItem(item: T) {
        val session = DbSessionUtil.getSessionFactory().currentSession
        val transaction = session.beginTransaction()
        try {
            session.update(item)
            transaction!!.commit()
        } catch (exception: Throwable) {
            transaction?.rollback()
            throw exception
        } finally {
            DbSessionUtil.closeSession()
        }
    }

    override fun deleteItem(item: T) {
        val session = DbSessionUtil.getSessionFactory().currentSession
        val transaction = session.beginTransaction()
        try {
            session.delete(item)
            transaction!!.commit()
        } catch (exception: Throwable) {
            transaction?.rollback()
            throw exception
        } finally {
            DbSessionUtil.closeSession()
        }
    }
}