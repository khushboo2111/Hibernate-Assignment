package com.springcore.manytomany;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.springcore.entity.Employee;
import com.springcore.entity.Project;

public class App {
    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            // Create projects
            Project p1 = new Project();
            p1.setProjectNumber(123);
            p1.setProjectName("Project 1");

            Project p2 = new Project();
            p2.setProjectNumber(124);
            p2.setProjectName("Project 2");

            Project p3 = new Project();
            p3.setProjectNumber(125);
            p3.setProjectName("Project 3");

            session.persist(p1);
            session.persist(p2);
            session.persist(p3);

            // Create employees
            Employee employee1 = new Employee();
            employee1.setEmployeeName("khushboo");

            Employee employee2 = new Employee();
            employee2.setEmployeeName("khush");

            // Assign projects to employees
            List<Project> projects1 = new ArrayList<>();
            projects1.add(p1);
            projects1.add(p2);
            employee1.setProject(projects1);

            List<Project> projects2 = new ArrayList<>();
            projects2.add(p2);
            projects2.add(p3);
            employee2.setProject(projects2);

            session.persist(employee1);
            session.persist(employee2);

            session.getTransaction().commit();

            // Query projects associated with employees
            session.beginTransaction();
            Query<Employee> employeeQuery = session.createQuery("from Employee", Employee.class);
            List<Employee> employees = employeeQuery.getResultList();
            for (Employee e : employees) {
                System.out.println("Employee: " + e.getEmployeeName());
                System.out.println("Projects:");
                for (Project p : e.getProject()) {
                    System.out.println("- " + p.getProjectName());
                }
            }
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
