package com.example.gauravsharma.bestoutofwaste.Model;
public class Address {
    String userID;
    String longitude;
    String latitude;
    String streetAddress1;
    String streetAddress2;
    String landMark;
    String city;
    String state;
    String pinCode;
    String country;

    public Address() {

    }

    public Address(String uid, String streetAddress1, String streetAddress2, String landMark, String city, String state, String country) {
        userID = uid;
        this.streetAddress1 =streetAddress1;
        this.streetAddress2 = streetAddress2;
        this.landMark = landMark;
        this.city = city;
        this.state = state;
        this.country = country;
    }
}
