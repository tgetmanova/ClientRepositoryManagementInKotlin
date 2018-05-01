package com.github.spb.tget.demo;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

import java.util.List;

public class Main {

    public static void main(String[] args){

        try {
            Session session = DbSessionUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Client");

            List<Client> clients = query.list();
            System.out.println("Rows number:     " + clients.size());
            clients.forEach(c -> System.out.println(c.getFirstName()));

            transaction.commit();
        } finally {

            DbSessionUtil.getSessionFactory().close();
        }
    }
}
