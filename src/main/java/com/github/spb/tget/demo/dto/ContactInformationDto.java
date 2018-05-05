package com.github.spb.tget.demo.dto;

public class ContactInformationDto {

    private String emailAddress;
    private PhoneDto phoneNumber;
    private AddressDto address;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public PhoneDto getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneDto phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }
}
