package com.github.spb.tget.demo.util

import org.hibernate.SessionFactory
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration

object DbSessionUtils {

    val sessionFactory: SessionFactory
        get() = buildSessionFactory()

    private fun buildSessionFactory(): SessionFactory {
        try {
            val configuration = Configuration()
                    .configure("hibernateConfiguration/hibernate.cfg.xml")

            val serviceRegistry = StandardServiceRegistryBuilder()
                    .applySettings(configuration.properties)
                    .build()

            return configuration.buildSessionFactory(serviceRegistry)
        } catch (exception: Throwable) {
            throw ExceptionInInitializerError(exception.message)
        }
    }

    fun closeSession() {
        sessionFactory.close()
    }
}
