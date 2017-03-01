package com.vakhnenko.hibernate.association.onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeveloperRunner {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();

        DeveloperRunner developerRunner = new DeveloperRunner();

        System.out.println("Creating the set of projects.");
        HashSet<Project2> projects1 = new HashSet<Project2>();
        projects1.add(new Project2("Proselyte Tutorial", "proselyte.net", 111));
        projects1.add(new Project2("SkybleLib", "SkybleSoft", 222));

        HashSet<Project2> projects2 = new HashSet<Project2>();
        projects2.add(new Project2("Some Project", "Some Company", 333));
        projects2.add(new Project2("One more Project", "One more Company", 444));

        System.out.println("Adding developer's records to the DB");

        int developerId1 = developerRunner.addDeveloper("Proselyte", "Developer", "Java Developer", 2, projects1);
        int developerId2 = developerRunner.addDeveloper("Peter", "UI", "UI Developer", 4, projects2);

        System.out.println("List of developers");
        developerRunner.listDevelopers();

        System.out.println("===================================");
        System.out.println("Updating Proselyte");
        developerRunner.updateDeveloper(developerId1, 3);

        System.out.println("Final list of developers");

        developerRunner.listDevelopers();
        System.out.println("===================================");
        sessionFactory.close();
    }

    public int addDeveloper(String firstName, String lastName, String specialty, int experience, Set<Project2> projects) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Developer developer = new Developer2(firstName, lastName, specialty, experience, 999);
        developer.setProjects(projects);
        int developerId = Integer.valueOf(String.valueOf(session.save(developer))).intValue(); //serializable to int
        transaction.commit();
        session.close();
        return developerId;
    }

    public void listDevelopers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List<Developer> developers = session.createQuery("FROM Developer2").list();
        for (Developer developer : developers) {
            System.out.println(developer);
            Set<Project> projects = developer.getProjects();
            for (Project project : projects) {
                System.out.println(project);
            }
            System.out.println("\n================\n");
        }
        session.close();
    }

    public void updateDeveloper(int developerId, int experience) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Developer developer = (Developer) session.get(Developer2.class, developerId);
        developer.setExperience(experience);
        session.update(developer);
        transaction.commit();
        session.close();
    }

    public void removeDeveloper(int developerId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Developer developer = (Developer) session.get(Developer2.class, developerId);
        session.delete(developer);
        transaction.commit();
        session.close();
    }
}
