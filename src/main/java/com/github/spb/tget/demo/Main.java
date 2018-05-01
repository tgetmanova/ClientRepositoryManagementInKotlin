package com.github.spb.tget.demo;

import com.github.spb.tget.demo.data.Client;
import com.github.spb.tget.demo.repository.ClientDbRepository;
import com.github.spb.tget.demo.repository.Repository;

public class Main {

    public static void main(String[] args) {
        Repository repository = ClientDbRepository.create();
        Client client = Client.random();
        repository.addItem(client);
        repository.getItems();
    }
}
