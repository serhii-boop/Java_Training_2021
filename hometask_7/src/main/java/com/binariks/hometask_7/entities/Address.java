package com.binariks.hometask_7.entities;


import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long address_id;

    @Column
    private String city;

    private String state;

    private int zip;

    private int apt_number;

    private String name;

    public Address(String city, String state, int zip, int apt_number, String name) {
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.apt_number = apt_number;
        this.name = name;
    }

    public Address(){}


    public Long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Long id) {
        this.address_id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public int getApt_number() {
        return apt_number;
    }

    public void setApt_number(int apt_number) {
        this.apt_number = apt_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + address_id +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", apt_number=" + apt_number +
                ", name='" + name + '\'' +
                '}';
    }
}
