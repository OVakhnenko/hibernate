package com.vakhnenko.hibernate.association.onetoone;

public class Contact {
    private int id;
    private String address;
    private String city;
    private String phoneNumber;
    private String email;

    public Contact() {
    }

    public Contact(String address, String city, String phoneNumber, String email) {
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Address: " + address +
                "\nCity: " + city +
                "\nPhone Number: " + phoneNumber +
                "\nemail: " + email + "\n";
    }
}
