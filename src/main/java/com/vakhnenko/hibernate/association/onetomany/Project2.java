package com.vakhnenko.hibernate.association.onetomany;

/**
 * Created for hibernate on 01.03.2017 13:05.
 */
public class Project2 extends Project {
    private int xxx;

    public Project2() {}

    public Project2(String projectName, String companyName, int xxx) {
        super(projectName, companyName);
        this.xxx = xxx;
    }

    public void setXxx(int xxx) {
        this.xxx = xxx;
    }

    public int getXxx() {
        return xxx;
    }
}
