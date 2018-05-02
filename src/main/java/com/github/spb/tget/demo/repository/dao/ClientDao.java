package com.github.spb.tget.demo.repository.dao;

import com.github.spb.tget.demo.repository.DbRepository;

public class ClientDao extends DbRepository {

    private ClientDao() {
        this.entityName = "Client";
    }

    public static ClientDao create() {
        return new ClientDao();
    }
}
