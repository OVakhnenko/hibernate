package com.vakhnenko.hibernate.association.manytomany;

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
        HashSet<Project> projects = new HashSet<Project>();
        projects.add(new Project("Proselyte Tutorial", "proselyte.net"));
        projects.add(new Project("SkybleLib", "SkybleSoft"));

        System.out.println("Adding developer's records to the DB");

        int developerId1 = developerRunner.addDeveloper("Proselyte", "Developer", "Java Developer", 2, projects);
        int developerId2 = developerRunner.addDeveloper("Peter", "UI", "UI Developer", 4, projects);

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

    public int addDeveloper(String firstName, String lastName, String specialty, int experience, Set<Project> projects) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Developer developer = new Developer(firstName, lastName, specialty, experience);
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
        List<Developer> developers = session.createQuery("FROM Developer").list();
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
        Developer developer = (Developer) session.get(Developer.class, developerId);
        developer.setExperience(experience);
        session.update(developer);
        transaction.commit();
        session.close();
    }

    public void removeDeveloper(int developerId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Developer developer = (Developer) session.get(Developer.class, developerId);
        session.delete(developer);
        transaction.commit();
        session.close();
    }
}
