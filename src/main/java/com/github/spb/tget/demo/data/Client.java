package com.github.spb.tget.demo.data;

import com.github.spb.tget.demo.util.RandomUtils;

import java.util.Set;

public class Client {

    private int clientId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String dateOfBirth;
    private Set<ContactInformation> contactInformation;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }


    public Set<ContactInformation> getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(Set<ContactInformation> contactInformation) {
        this.contactInformation = contactInformation;
    }

    public Client withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Client withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public static Client random() {
        return new Client()
                .withFirstName(RandomUtils.getRandomAlphabetic(15))
                .withLastName(RandomUtils.getRandomAlphabetic(15));
    }
}
