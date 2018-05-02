package com.github.spb.tget.demo.repository.dao;

import com.github.spb.tget.demo.repository.DbRepository;

public class CountryDao extends DbRepository {

    private CountryDao() {
        this.entityName = "Country";
    }

    public static CountryDao create() {
        return new CountryDao();
    }
}
