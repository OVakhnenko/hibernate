package com.vakhnenko.hibernate.mappings.sortedset;

import java.util.Comparator;

public class ProjectsComparator implements Comparator<Project> {
    public int compare(Project o1, Project o2) {
        final int BEFORE = -1;
        final int AFTER = 1;

        if (o2 == null) {
            return BEFORE * -1;
        }

        Comparable thisProject = o1.getProjectName();
        Comparable thatProject = o2.getProjectName();

        if (thisProject == null) {
            return AFTER * 1;
        } else if (thatProject == null) {
            return BEFORE * -1;
        } else {
            return thisProject.compareTo(thatProject) * -1;
        }
    }
}
