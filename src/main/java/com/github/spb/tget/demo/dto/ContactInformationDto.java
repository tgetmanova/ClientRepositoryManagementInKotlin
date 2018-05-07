package com.github.spb.tget.demo.dto;

public class ContactInformationDto {

    private String emailAddress;
    private PhoneDto phone;
    private AddressDto address;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public PhoneDto getPhone() {
        return phone;
    }

    public void setPhone(PhoneDto phone) {
        this.phone = phone;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }
}
