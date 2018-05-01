package com.github.spb.tget.demo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DbRepository implements Repository {

    @Override
    public List<Client> getItems() {
        List<Client> clients;
        try {
            Session session = DbSessionUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Client");

            clients = query.list();
            System.out.println("Rows number:     " + clients.size());
            clients.forEach(c -> System.out.println(c.getFirstName()));

            transaction.commit();
        } finally {

            DbSessionUtil.getSessionFactory().close();
        }

        return clients;
    }
}
