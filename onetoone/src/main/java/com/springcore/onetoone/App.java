package com.springcore.onetoone;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.springcore.onetoone.entity.AadharCard;
import com.springcore.onetoone.entity.Employee;

public class App {
    public static void main(String[] args) {
        
    		try {
            SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Employee employee = new Employee();
            employee.setEmployeeName("khushboo");
            AadharCard acard = new AadharCard();
            acard.setAadharNumber(12344);
            employee.setAadhar_number(acard);
            acard.setEmployee(employee);

            Employee employee1 = new Employee();
            employee1.setEmployeeName("Ashish");
            AadharCard acard1 = new AadharCard();
            acard1.setAadharNumber(12345);
            employee1.setAadhar_number(acard1);
            acard1.setEmployee(employee1);

            session.persist(acard);
            session.persist(acard1);
            session.persist(employee);
            session.persist(employee1);
            session.getTransaction().commit();

            // Query employees
            Query<Employee> employeeQuery = session.createQuery("from Employee", Employee.class);
            List<Employee> employees = employeeQuery.getResultList();
            for (Employee e : employees) {
                System.out.println("Employee: " + e.getEmployeeName() + ", Aadhar Number: " + e.getAadhar_number().getAadharNumber());
            }

            // Query Aadhar cards
            Query<AadharCard> aadharQuery = session.createQuery("from AadharCard", AadharCard.class);
            List<AadharCard> aadharCards = aadharQuery.getResultList();
            for (AadharCard a : aadharCards) {
                System.out.println("Aadhar Card: " + a.getAadharNumber() + ", Employee: " + a.getEmployee().getEmployeeName());
            }
    		}
    		catch (Exception e) {
                e.printStackTrace();
            } 

         
    }
}
