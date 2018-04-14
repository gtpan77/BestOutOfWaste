package com.example.gauravsharma.bestoutofwaste.Model;

import java.util.Date;
public class User {
    public String userID;
    public String firstName;
    public String lastName;
    public String emailAddress;
    public String gender;
    public Date dateOfBirth;
    public String phoneNumber;
    public String address;
    public Address pickUpAddress;
    public boolean flag;
    public boolean registered;
    public int lastCount;
    public Date lastSeen;
    public Date updateAccount;
    public Date createAccount;

    public User() {

    }

    public User(String uid, String firstName, String lastName, String emailAddress, String phoneNumber, String address, boolean registered) {
        userID = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.registered = registered;
    }
}
