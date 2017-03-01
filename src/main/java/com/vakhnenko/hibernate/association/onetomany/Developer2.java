package com.vakhnenko.hibernate.association.onetomany;

/**
 * Created for hibernate on 01.03.2017 11:54.
 */
public class Developer2 extends Developer {
    private int xxx;

    public Developer2() {}

    public Developer2(String firstName, String lastName, String specialty, int experience, int xxx) {
        super(firstName, lastName, specialty, experience);
        this.xxx = xxx;
    }

    public void setXxx(int xxx) {
        this.xxx = xxx;
    }

    public int getXxx() {
        return xxx;
    }
}
