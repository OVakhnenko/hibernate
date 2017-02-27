package com.vakhnenko.hibernate.mappings.sortedset;

public class Project implements Comparable<Project> {
    private int id;
    private String projectName;
    private String companyName;

    /**
     * Constructors
     */
    public Project() {
    }

    public Project(String projectName, String companyName) {
        this.projectName = projectName;
        this.companyName = companyName;
    }


    /**
     * Getters and Setters
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCompanyName() {
        return companyName;
    }


    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int compareTo(Project that) {
        final int BEFORE = -1;
        final int AFTER = 1;

        if (that == null) {
            return BEFORE;
        }

        Comparable thisProject = this.getProjectName();
        Comparable thatProject = that.getProjectName();

        if (thisProject == null) {
            return AFTER;
        } else if (thatProject == null) {
            return BEFORE;
        } else {
            return thisProject.compareTo(thatProject);
        }
    }

    @Override
    public String toString() {
        return "Project:\n" +
                "id: " + id +
                "\nProject Name: " + projectName +
                "\nCompany Name: " + companyName + "\n";
    }
}
