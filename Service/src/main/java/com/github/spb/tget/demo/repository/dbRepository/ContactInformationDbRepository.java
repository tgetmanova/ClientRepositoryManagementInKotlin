package com.github.spb.tget.demo.repository.dbRepository;

public class ContactInformationDbRepository extends DbRepository {

    private ContactInformationDbRepository() {
        this.entityName = "ContactInformation";
    }

    public static ContactInformationDbRepository create() {
        return new ContactInformationDbRepository();
    }
}
