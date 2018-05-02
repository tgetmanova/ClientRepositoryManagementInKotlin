package com.github.spb.tget.demo.repository.dbRepository;

import com.github.spb.tget.demo.repository.DbRepository;

public class ClientDbRepository extends DbRepository {

    private ClientDbRepository() {
        this.entityName = "Client";
    }

    public static ClientDbRepository create() {
        return new ClientDbRepository();
    }
}
