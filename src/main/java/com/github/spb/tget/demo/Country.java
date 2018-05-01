package com.github.spb.tget.demo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name = "Countries")
public class Country {

    @Column(name = "Name")
    private String name;

    @Id
    @Column(name = "Code")
    private String code;
}
