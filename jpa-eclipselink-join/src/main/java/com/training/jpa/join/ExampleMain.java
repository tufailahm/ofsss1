package com.training.jpa.join;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ExampleMain {
    private static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ahmed");

    public static void main(String[] args) {
        try {
            //persistEmployees();
            executeQuery();
        } finally {
            entityManagerFactory.close();
        }
    }

    public static void persistEmployees() {
        Task task1 = new Task("Coding", "Mohammad");
        Task task2 = new Task("Refactoring", "Neha");
        Task task3 = new Task("Designing", "Isha");
        Task task4 = new Task("Documentation", "Karthik");

        Employee employee1 = Employee.create("Aman", task1, task3);
        Employee employee2 = Employee.create("Tarun", task2, task4);
        Employee employee3 = Employee.create("Pooja", task3, task4);
        Employee employee4 = Employee.create("Harish");

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee1);
        em.persist(employee2);
        em.persist(employee3);
        em.persist(employee4);
        em.getTransaction().commit();
        em.close();
        System.out.println("-- Employee persisted --");
        System.out.println(employee1);
        System.out.println(employee2);
        System.out.println(employee3);
        System.out.println(employee4);
    }

    private static void executeQuery() {
        System.out.println("-- executing query --");
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("SELECT DISTINCT e FROM Employee e INNER JOIN e.tasks t");
        List<Employee> resultList = query.getResultList();
        resultList.forEach(System.out::println);
        em.close();
    }
}