package com.github.spb.tget.demo.dao;

import com.github.spb.tget.demo.data.ClientEntity;
import com.github.spb.tget.demo.data.ContactInformation;
import com.github.spb.tget.demo.repository.Repository;
import com.github.spb.tget.demo.repository.RepositoryFactory;

import java.util.List;

public class ContactDao {

    private Repository contactInformationRepository;
    private String repoType = System.getProperty("repoType");

    public ContactDao() {
        contactInformationRepository = RepositoryFactory.Companion.getContactInformationRepositoryByType(repoType);
    }

    public List<ContactInformation> getAllContacts() {
        return contactInformationRepository.getItems();
    }

    public void addContacts(List<ContactInformation> contacts, ClientEntity client) {
        contacts.forEach(c -> {
            c.setClient(client);
            contactInformationRepository.addItem(c);
        });
    }
}
