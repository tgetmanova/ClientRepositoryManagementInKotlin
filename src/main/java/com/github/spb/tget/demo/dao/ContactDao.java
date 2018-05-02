package com.github.spb.tget.demo.dao;

import com.github.spb.tget.demo.data.ContactInformation;
import com.github.spb.tget.demo.repository.dbRepository.ContactInformationDbRepository;

import java.util.List;

public class ContactDao {

    private ContactInformationDbRepository contactInformationDbRepository;

    public ContactDao() {
        this.contactInformationDbRepository = ContactInformationDbRepository.create();
    }

    public List<ContactInformation> getAllContacts() {
       return contactInformationDbRepository.getItems();
    }
}
