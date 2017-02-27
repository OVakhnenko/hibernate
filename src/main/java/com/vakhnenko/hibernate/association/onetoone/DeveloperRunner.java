package com.vakhnenko.hibernate.association.onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class DeveloperRunner {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        DeveloperRunner developerRunner = new DeveloperRunner();

        System.out.println("Adding contact information...");
        Contact contact1 = developerRunner.addContact("Some address", "Dnipropetrovsk", "+380501234567", "proselytear@yahoo.com");
        Contact contact2 = developerRunner.addContact("One More address", "Kyiv", "+380667654321", "peter@mail.com");

        System.out.println("Creating developer's records...");
        Integer developerId1 = developerRunner.addDeveloper("Proselyte", "Developer", "Java Developer", 2, contact1);
        Integer developerId2 = developerRunner.addDeveloper("Peter", "Programmer", "C++ Developer", 2, contact2);

        System.out.println("List of Developers: ");
        developerRunner.listDevelopers();

        System.out.println("Updating experience of Proselyte to 3 years and removing Peter...");
        developerRunner.updateDeveloper(developerId1, 3);
        developerRunner.removeDeveloper(developerId2);

        System.out.println("Final list of Developers: ");
        developerRunner.listDevelopers();

        sessionFactory.close();
    }

    public Integer addDeveloper(String firstName, String lastName, String specialty, int experience, Contact contact) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Integer developerId = null;

        transaction = session.beginTransaction();
        Developer developer = new Developer(firstName, lastName, specialty, experience, contact);
        developerId = (Integer) session.save(developer);
        transaction.commit();
        session.close();
        return developerId;
    }

    public Contact addContact(String address, String city, String phoneNumber, String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Contact contact = new Contact(address, city, phoneNumber, email);
        session.save(contact);
        transaction.commit();
        session.close();

        return contact;
    }

    public void listDevelopers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List<Developer> developers = session.createQuery("FROM Developer").list();
        for (Developer developer : developers) {
            System.out.println(developer);
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
