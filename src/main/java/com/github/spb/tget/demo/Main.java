package com.github.spb.tget.demo;

import com.github.spb.tget.demo.data.Client;
import com.github.spb.tget.demo.repository.ClientDbRepository;
import com.github.spb.tget.demo.repository.Repository;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Repository repository = ClientDbRepository.create();
        repository.updateItem(((Client)repository.getItems().get(0)).withFirstName("hyhyhyyy"));
        List<Client> c = repository.getItems();
    }
}
