package com.microservice.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;


public class RequestPayload implements Serializable {

    @JsonProperty("account_number")
    private int accountNumber;
    @JsonProperty("name")
    private String name;
    @JsonProperty("contact_information")
    private Contact contactInformation;
    @JsonProperty("date-of-birth")
    private Date dob;
    @JsonProperty("address")
    private Address address;

    public RequestPayload(int accountNumber, String name, Contact contactInformation, Date dob, Address address) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.contactInformation = contactInformation;
        this.dob = dob;
        this.address = address;
    }

    @Override
    public String toString() {
        return "RequestPayload{" +
                "accountNumber=" + accountNumber +
                ", name='" + name + '\'' +
                ", contactNo=" + contactInformation.toString() +
                ", dob=" + dob +
                ", address=" + address.toString() +
                '}';
    }


}

