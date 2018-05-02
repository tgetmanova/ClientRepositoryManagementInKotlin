package com.github.spb.tget.demo;

import com.github.spb.tget.demo.data.Client;
import com.github.spb.tget.demo.repository.ClientDbRepository;
import com.github.spb.tget.demo.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Repository repository = ClientDbRepository.create();

        List<Client> clients = repository.getItems();
        clients.forEach(c -> {
            if (c.getContactInformation() != null && !c.getContactInformation().isEmpty()) {
                System.out.println(new ArrayList<>(c.getContactInformation()).get(0).getAddress());
            }
        });
    }
}
