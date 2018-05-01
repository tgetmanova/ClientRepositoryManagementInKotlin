package com.github.spb.tget.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "Clients")
public class Client {

    public Client() {
    }

    @Id
    @GeneratedValue
    @Column(name="ClientID")
    private int clientId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "MiddleName")
    private String middleName;

    @Column(name = "DateOfBirth")
    private String dateOfBirth;

    public String getFirstName() {
        return firstName;
    }
}
