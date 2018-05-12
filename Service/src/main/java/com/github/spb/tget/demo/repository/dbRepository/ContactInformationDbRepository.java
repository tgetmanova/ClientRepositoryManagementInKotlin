package com.github.spb.tget.demo.repository.dbRepository;

import com.github.spb.tget.demo.repository.DbRepository;

public class ContactInformationDbRepository extends DbRepository {

    private ContactInformationDbRepository() {
        this.entityName = "ContactInformation";
    }

    public static ContactInformationDbRepository create() {
        return new ContactInformationDbRepository();
    }
}
