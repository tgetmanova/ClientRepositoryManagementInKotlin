package com.github.spb.tget.demo.repository;

public class ClientDbRepository extends DbRepository {

    private ClientDbRepository() {
        this.entityName = "Client";
    }

    public static ClientDbRepository create() {
        return new ClientDbRepository();
    }
}
