package com.github.spb.tget.demo.dao;

import com.github.spb.tget.demo.data.Client;
import com.github.spb.tget.demo.data.ContactInformation;
import com.github.spb.tget.demo.repository.Repository;
import com.github.spb.tget.demo.repository.dbRepository.ContactInformationDbRepository;
import com.github.spb.tget.demo.repository.inplaceRepository.ContactInformationInplaceRepository;

import java.util.List;

public class ContactDao {

    private Repository<ContactInformation> contactInformationRepository;

    public ContactDao() {
        initRepositoriesByType();
    }

    private void initRepositoriesByType() {
        String repoType = System.getProperty("repoType");
        switch (repoType) {
            case "db":
                this.contactInformationRepository = ContactInformationDbRepository.create();
                break;
            case "inplace":
                this.contactInformationRepository = new ContactInformationInplaceRepository();
                break;
            default:
                throw new IllegalStateException("Unknown repository type: " + repoType);
        }
    }

    public List<ContactInformation> getAllContacts() {
        return contactInformationRepository.getItems();
    }

    public void addContacts(List<ContactInformation> contacts, Client client) {
        contacts.forEach(c -> {
            c.setClient(client);
            contactInformationRepository.addItem(c);
        });
    }
}
