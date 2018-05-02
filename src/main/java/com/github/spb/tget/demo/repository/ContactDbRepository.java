package com.github.spb.tget.demo.repository;

public class ContactDbRepository extends DbRepository {

    private ContactDbRepository() {
        this.entityName = "ContactInformation";
    }

    public static ContactDbRepository create() {
        return new ContactDbRepository();
    }
}
