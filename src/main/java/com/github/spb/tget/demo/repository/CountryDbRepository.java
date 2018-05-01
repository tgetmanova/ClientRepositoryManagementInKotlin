package com.github.spb.tget.demo.repository;

public class CountryDbRepository extends DbRepository {

    private CountryDbRepository() {
        this.entityName = "Country";
    }

    public static CountryDbRepository create() {
        return new CountryDbRepository();
    }
}
