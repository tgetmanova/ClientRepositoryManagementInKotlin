package com.github.spb.tget.demo.converter;

import com.github.spb.tget.demo.data.Client;
import com.github.spb.tget.demo.data.ContactInformation;
import com.github.spb.tget.demo.dto.AddressDto;
import com.github.spb.tget.demo.dto.ClientDto;

import org.apache.commons.lang3.StringUtils;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class ClientConverter {

    public Client fromDto(ClientDto clientDto) {
        Client client = new Client();

        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setMiddleName(clientDto.getMiddleName());
        client.setDateOfBirth(
                Date.valueOf(clientDto.getDateOfBirth().toLocalDate().toString()));
        Set<ContactInformation> contactInfoSet = new HashSet<>();

        if (clientDto.getContacts() != null && !clientDto.getContacts().isEmpty()) {
            clientDto.getContacts().forEach(contact -> {
                ContactInformation contactInfo = new ContactInformation();

                contactInfo.setEmail(contact.getEmailAddress());

                if (contact.getPhoneNumber() != null) {
                    String phone = String.format("+%d-%s",
                            contact.getPhoneNumber().getCountryCode(),
                            contact.getPhoneNumber().getPhoneNumber());
                    if (contact.getPhoneNumber().getExtension() != null) {
                        phone += contact.getPhoneNumber().getExtension();
                    }
                    contactInfo.setPhone(phone);
                }

                if (contact.getAddress() != null) {
                    StringBuilder addressBuilder = new StringBuilder();
                    AddressDto addressDto = contact.getAddress();
                    if (!StringUtils.isBlank(addressDto.getCountry())) {
                        addressBuilder.append(addressDto.getCountry());
                    }
                    if (!StringUtils.isBlank(addressDto.getState())) {
                        addressBuilder.append(addressDto.getState());
                    }
                    if (!StringUtils.isBlank(addressDto.getPostalCode())) {
                        addressBuilder.append(addressDto.getPostalCode());
                    }
                    if (!StringUtils.isBlank(addressDto.getAddressLine())) {
                        addressBuilder.append(addressDto.getAddressLine());
                    }
                    contactInfo.setAddress(addressBuilder.toString());
                }

                if (!StringUtils.isAllBlank(contactInfo.getAddress(),
                        contactInfo.getEmail(), contactInfo.getPhone())) {
                    contactInfoSet.add(contactInfo);
                }
            });
        }

        if (!contactInfoSet.isEmpty()) {
            client.setContactInformation(contactInfoSet);
        }

        return client;
    }

    public ClientDto toDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setMiddleName(client.getMiddleName());
        clientDto.setDateOfBirth(client.getDateOfBirth().toLocalDate().atStartOfDay());
        // TODO
        clientDto.setContacts(null);
        return clientDto;
    }
}
