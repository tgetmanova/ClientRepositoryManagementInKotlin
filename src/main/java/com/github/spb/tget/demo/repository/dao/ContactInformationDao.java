package com.github.spb.tget.demo.repository.dao;

import com.github.spb.tget.demo.repository.DbRepository;

public class ContactInformationDao extends DbRepository {

    private ContactInformationDao() {
        this.entityName = "ContactInformation";
    }

    public static ContactInformationDao create() {
        return new ContactInformationDao();
    }
}
