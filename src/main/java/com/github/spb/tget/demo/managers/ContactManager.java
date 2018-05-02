package com.github.spb.tget.demo.managers;

import com.github.spb.tget.demo.data.ContactInformation;
import com.github.spb.tget.demo.repository.dao.ContactInformationDao;

import java.util.List;

public class ContactManager {

    private ContactInformationDao contactInformationDao;

    public ContactManager() {
        this.contactInformationDao = ContactInformationDao.create();
    }

    public List<ContactInformation> getAllContacts() {
       return contactInformationDao.getItems();
    }
}
