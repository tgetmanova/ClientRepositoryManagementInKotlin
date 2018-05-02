package com.github.spb.tget.demo.repository.dbRepository;

import com.github.spb.tget.demo.repository.DbRepository;

public class CountryDbRepository extends DbRepository {

    private CountryDbRepository() {
        this.entityName = "Country";
    }

    public static CountryDbRepository create() {
        return new CountryDbRepository();
    }
}
