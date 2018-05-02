package com.github.spb.tget.demo;

import com.github.spb.tget.demo.data.Client;
import com.github.spb.tget.demo.managers.ClientManager;

public class Main {

    public static void main(String[] args) {
        ClientManager clientManager = new ClientManager();
        Client client = clientManager.createRandomClientWithContactInformation();
        System.out.println(client.prettyPrint());
    }
}
