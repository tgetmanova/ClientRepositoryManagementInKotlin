package com.github.spb.tget.demo.converter;

import com.github.spb.tget.demo.data.ClientEntity;
import com.github.spb.tget.demo.data.ContactInformation;
import com.github.spb.tget.demo.dto.AddressDto;
import com.github.spb.tget.demo.dto.ClientDto;
import com.github.spb.tget.demo.dto.ContactInformationDto;
import com.github.spb.tget.demo.dto.PhoneDto;

import org.apache.commons.lang3.StringUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.github.spb.tget.demo.util.CommonUtilsKt.getSubstringBetweenOrToEnd;

public class ClientConverter {

    public ClientEntity fromDto(ClientDto clientDto) {
        ClientEntity client = new ClientEntity();

        client.setClientId(clientDto.getId());
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setMiddleName(clientDto.getMiddleName());
        client.setDateOfBirth(
                Date.valueOf(clientDto.getDateOfBirth().toLocalDate().toString()));
        Set<ContactInformation> contactInfoSet = new HashSet<>();

        if (clientDto.getContacts() != null && !clientDto.getContacts().isEmpty()) {
            clientDto.getContacts().forEach(contact -> {
                ContactInformation contactInfo = contactInfoFromDto(contact);
                if (!StringUtils.isAllBlank(contactInfo.getAddress(),
                        contactInfo.getEmail(), contactInfo.getPhone())) {
                    contactInfoSet.add(contactInfo);
                }
            });
        }

        client.setContactInformation(contactInfoSet);

        return client;
    }

    public ClientDto toDto(ClientEntity client) {
        ClientDto clientDto = new ClientDto();

        clientDto.setId(client.getClientId());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setMiddleName(client.getMiddleName());
        clientDto.setDateOfBirth(client.getDateOfBirth() == null
                ? null : client.getDateOfBirth().toLocalDate().atStartOfDay());

        List<ContactInformationDto> contactInformationDtos = new ArrayList<>();

        client.getContactInformation().forEach(ci -> {
            ContactInformationDto contactInformationDto = contactInfoToDto(ci);
            contactInformationDtos.add(contactInformationDto);
        });

        clientDto.setContacts(contactInformationDtos);
        return clientDto;
    }

    public ContactInformationDto contactInfoToDto(ContactInformation contactInformation) {
        ContactInformationDto contactInformationDto = new ContactInformationDto();
        contactInformationDto.setEmailAddress(contactInformation.getEmail());
        contactInformationDto.setAddress(addressToDto(contactInformation.getAddress()));
        contactInformationDto.setPhone(phoneToDto(contactInformation.getPhone()));
        return contactInformationDto;
    }

    private AddressDto addressToDto(String address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setCountry(getSubstringBetweenOrToEnd(address, "Country: ", ";"));
        addressDto.setState(getSubstringBetweenOrToEnd(address, "State: ", ";"));
        addressDto.setAddressLine(getSubstringBetweenOrToEnd(address, "Street address: ", ";"));
        addressDto.setPostalCode(getSubstringBetweenOrToEnd(address, "Postal code: ", ";"));

        return addressDto;
    }

    private PhoneDto phoneToDto(String phone) {
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setCountryCode(Integer.parseInt(
                StringUtils.substringBetween(phone, "+", "-")));
        if (phone.contains("ext.")) {
            phoneDto.setPhoneNumber(StringUtils.substringBetween(phone, "-", "ext.").trim());
            phoneDto.setExtension(Integer.parseInt(StringUtils.substringAfter(phone, "ext.").trim()));
        } else {
            phoneDto.setPhoneNumber(StringUtils.substringAfter(phone, "-"));
        }
        return phoneDto;
    }

    public ContactInformation contactInfoFromDto(ContactInformationDto contactDto) {
        ContactInformation contactInfo = new ContactInformation();

        contactInfo.setEmail(contactDto.getEmailAddress());

        if (contactDto.getPhone() != null) {
            String phone = String.format("+%d-%s",
                    contactDto.getPhone().getCountryCode(),
                    contactDto.getPhone().getPhoneNumber());
            if (contactDto.getPhone().getExtension() != null) {
                phone += String.format(" ext. %d", contactDto.getPhone().getExtension());
            }
            contactInfo.setPhone(phone);
        }

        if (contactDto.getAddress() != null) {
            List<String> addressParts = new ArrayList<>();
            AddressDto addressDto = contactDto.getAddress();
            if (!StringUtils.isBlank(addressDto.getAddressLine())) {
                addressParts.add(String.format("Street address: %s", addressDto.getAddressLine()));
            }
            if (!StringUtils.isBlank(addressDto.getPostalCode())) {
                addressParts.add(String.format("Postal code: %s", addressDto.getPostalCode()));
            }
            if (!StringUtils.isBlank(addressDto.getCountry())) {
                addressParts.add(String.format("Country: %s", addressDto.getCountry()));
            }
            if (!StringUtils.isBlank(addressDto.getState())) {
                addressParts.add(String.format("State: %s", addressDto.getState()));
            }
            contactInfo.setAddress(String.join("; ", addressParts));
        }

        return contactInfo;
    }
}
