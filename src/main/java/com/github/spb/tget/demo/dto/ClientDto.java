package com.github.spb.tget.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ClientDto {

    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDateTime dateOfBirth;
    private List<ContactInformationDto> contacts;

    public String getFirstName() {
        return firstName;
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

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<ContactInformationDto> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactInformationDto> contacts) {
        this.contacts = contacts;
    }
}
