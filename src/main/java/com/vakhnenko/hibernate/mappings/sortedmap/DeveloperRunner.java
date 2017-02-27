package net.proselyte.hibernate.mappings.sortedmap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class DeveloperRunner {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();

        DeveloperRunner developerRunner = new DeveloperRunner();

        System.out.println("Creating the TreeMaps of projects.");

        TreeMap<String, Project> projects1 = new TreeMap<String, Project>();
        projects1.put("Computer Science", new Project("Proselyte Tutorial", "proselyte.net"));
        projects1.put("Aviation", new Project("SkybleLib", "SkybleSoft"));

        TreeMap<String, Project> projects2 = new TreeMap<String, Project>();
        projects2.put("Some Sphere", new Project("Some Project", "Some Company"));
        projects2.put("E-commerce", new Project("One more Project", "One more Company"));

        System.out.println("Adding developer's records to the DB");

        Integer developerId1 = developerRunner.addDeveloper("Proselyte", "Developer", "Java Developer", 2, projects1);
        Integer developerId2 = developerRunner.addDeveloper("Peter", "UI", "UI Developer", 4, projects2);

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

    public Integer addDeveloper(String firstName, String lastName, String specialty, int experience, TreeMap<String, Project> projects) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Integer developerId = null;

        transaction = session.beginTransaction();
        Developer developer = new Developer(firstName, lastName, specialty, experience);
        developer.setProjects(projects);
        developerId = (Integer) session.save(developer);
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
            SortedMap<String, Project> projects = developer.getProjects();
            System.out.println(developer.getFirstName() + "'s projects:\n");
            System.out.println(projects);
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
