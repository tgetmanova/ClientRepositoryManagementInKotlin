package com.github.spb.tget.demo.repository.dbRepository;

import com.github.spb.tget.demo.data.Client;

public class ClientDbRepository extends DbRepository<Client> {

    private ClientDbRepository() {
        this.entityName = "Client";
    }

    public static ClientDbRepository create() {
        return new ClientDbRepository();
    }
}
