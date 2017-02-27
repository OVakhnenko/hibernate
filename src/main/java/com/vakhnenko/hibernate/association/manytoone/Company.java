package com.vakhnenko.hibernate.association.manytoone;

public class Company {
    private int id;
    private String companyName;

    public Company() {
    }

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Company:" +
                "\nCompany ID: " + id +
                "\nCompany Name: " + companyName + "\n";
    }
}
