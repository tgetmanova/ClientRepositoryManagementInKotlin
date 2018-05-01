package com.github.spb.tget.demo.repository;

import com.github.spb.tget.demo.util.DbSessionUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DbRepository<T> implements Repository {

    protected String entityName;

    @Override
    public List<T> getItems() {
        List<T> items;
        try {
            Session session = DbSessionUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from " + entityName);

            items = query.list();
            System.out.println("Rows number:     " + items.size());

            transaction.commit();
        } finally {
            DbSessionUtil.closeSession();
        }

        return items;
    }

    @Override
    public int addItem(Object item) {
        Session session = DbSessionUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        int id = -1;
        try {
            id = (Integer) session.save(item);
            transaction.commit();
        } catch (Throwable exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            DbSessionUtil.closeSession();
        }

        return id;
    }

    @Override
    public void updateItem(Object item) {
        Session session = DbSessionUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(item);
            transaction.commit();
        } catch (Throwable exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            DbSessionUtil.closeSession();
        }
    }

    @Override
    public void deleteItem(Object item) {
        Session session = DbSessionUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(item);
            transaction.commit();
        } catch (Throwable exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            DbSessionUtil.closeSession();
        }
    }
}
