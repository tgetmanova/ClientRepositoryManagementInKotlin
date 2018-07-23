package com.github.spb.tget.demo.data;

import com.github.spb.tget.demo.util.RandomUtils;

public class ContactInformation {

    private Client client;
    private int contactId;
    private String phone;
    private String address;
    private String email;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ContactInformation withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactInformation withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ContactInformation withAddress(String address) {
        this.address = address;
        return this;
    }

    public static ContactInformation random() {
        return new ContactInformation()
                .withAddress(String.format("Country: %s; State: %s; Street address: %s; Postal code: %s",
                        RandomUtils.Companion.getRandomAlphabetic(10),
                        RandomUtils.Companion.getRandomAlphabetic(10),
                        RandomUtils.Companion.getRandomString(60),
                        RandomUtils.Companion.getRandomString(20)))
                .withEmail(RandomUtils.Companion.getRandomEmailAddress())
                .withPhone(String.format("+%s-%s ext.%s",
                        RandomUtils.Companion.getRandomNumeric(3),
                        RandomUtils.Companion.getRandomNumeric(10),
                        RandomUtils.Companion.getRandomNumeric(5)));
    }

    @Override
    public String toString() {
        return String.format("ContactID: [%d],  Address: %s, Phone: %s, Email: %s",
                getContactId(), getAddress(), getPhone(), getEmail());
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof ContactInformation)) {
            return false;
        }

        ContactInformation otherContact = (ContactInformation) other;
        if (this.getContactId() > 0 && otherContact.getContactId() > 0) {
            return this.getContactId() == otherContact.getContactId();
        }
        return (this.getAddress() != null && this.getAddress().equals(otherContact.getAddress()))
                && (this.getPhone() != null && this.getPhone().equals(otherContact.getPhone()))
                && (this.getEmail() != null && this.getEmail().equals(otherContact.getEmail()));
    }
}