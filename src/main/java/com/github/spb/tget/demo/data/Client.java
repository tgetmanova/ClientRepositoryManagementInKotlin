package com.github.spb.tget.demo.data;

import com.github.spb.tget.demo.util.RandomUtils;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Client {

    private int clientId;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date dateOfBirth;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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

    public Client withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public Client withDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public static Client random() {
        return new Client()
                .withFirstName(RandomUtils.getRandomAlphabetic(15))
                .withLastName(RandomUtils.getRandomAlphabetic(15))
                .withMiddleName(RandomUtils.getRandomAlpanumeric(15));
        // .withDateOfBirth(Date.valueOf(RandomUtils.randomDateOfBirthAsAdult().toLocalDate()));
    }

    public Client withRandomContactInformation() {
        this.contactInformation = new HashSet<>();
        ContactInformation contactInfo = ContactInformation.random();
        this.contactInformation.add(contactInfo);
        contactInfo.setClient(this);
        return this;
    }

    @Override
    public String toString() {
        return String.format("ClientID: [%d] %s %s", getClientId(), getFirstName(), getLastName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        if (this.getClientId() > 0 && otherClient.getClientId() > 0) {
            return this.clientId == otherClient.getClientId();
        }
        return (this.getFirstName() != null && this.getFirstName().equals(otherClient.getFirstName()))
                && (this.getLastName() != null && this.getLastName().equals(otherClient.getLastName()))
                && (this.getMiddleName() != null && this.getMiddleName().equals(otherClient.getMiddleName()));
    }

    public Client withContactInformation(Set<ContactInformation> contactInformation) {
        this.contactInformation = contactInformation;
        return this;
    }
}
