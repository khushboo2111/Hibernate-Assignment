package com.springcore.hibernateproject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.springcore.hibernateproject.entity.Employee;
import com.springcore.hibernateproject.entity.Name;

public class App 
{
    public static void main( String[] args )
    {
        Employee employee = new Employee();
        employee.setEmpName("Khushboo");
        employee.setEmpEmail("guptakhushboo81537@gmail.com");
        
        Name name = new Name();
        name.setFirstName("Khushboo");
        name.setMiddleName("");
        name.setLastName("Gupta");
        employee.setName(name);
        
        Employee employee1 = new Employee();
        employee1.setEmpName("Ankush");
        employee1.setEmpEmail("ankushsingh123@gmail.com");

        Employee employee2 = new Employee();
        employee2.setEmpName("Aisha");
        employee2.setEmpEmail("aishatiwari321@gmail.com");
        
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        
        //Create query 
        session.beginTransaction();
        //session.persist(name);
        session.persist(employee);
        session.persist(employee1);
        session.persist(employee2);
        session.getTransaction().commit();
        
        //Read query
        session.createQuery("from Employee", Employee.class).list().forEach(System.out::println);   
         //HQL query
//        String query1 = "select e.employeeName, e.employeeEmail from Employee as e";
//        Query q = session.createQuery(query1);
        session.beginTransaction();
        Employee savedEmployee = session.get(Employee.class, 1L);
        System.out.println(savedEmployee);
        session.getTransaction().commit();
         
        //Update query
        //HQL query
//	    Query q1 = session.createQuery("update Employee set employeeName = :newName where employeeName = :oldName");
//	    q1.setParameter("newName", "khush"); // Use "newName" as the named parameter
//	    q1.setParameter("oldName", "khushboo");
        
        session.beginTransaction();
        employee.setEmpEmail("khushboo.gupta1@accolitedigital.com");
        session.merge(employee);
        session.getTransaction().commit();
        session.createQuery("from Employee", Employee.class).list().forEach(System.out::println);
        
       //Delete query
       //HQL query 
//    session.beginTransaction();
//    Query q2 = session.createQuery("delete from Employee e where e.employeeName = 'aisha'");
        session.beginTransaction();
        session.remove(employee2);
        session.getTransaction().commit();
        session.createQuery("from Employee", Employee.class).list().forEach(System.out::println);
         
         
        session.close();
        
        
    }
}
