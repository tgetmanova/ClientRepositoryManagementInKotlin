package com.github.spb.tget.demo.repository;

import java.util.List;

public interface Repository<T> {

    List<T> getItems();

    void addItem(T item);

    void updateItem(T item);

    void deleteItem(T item);

}
