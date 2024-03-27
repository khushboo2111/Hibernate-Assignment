package com.springcore.mappingsproject;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.springcore.entity.Address;
import com.springcore.entity.Employee;

public class App {
    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Employee employee = new Employee();
            employee.setEmployeeName("khushboo");

            Address address = new Address();
            address.setHouseNumber("918");
            address.setCity("Noida");
            address.setState("Uttar Pradesh");
            address.setEmployee(employee);

            Address address1 = new Address();
            address1.setHouseNumber("123");
            address1.setCity("Delhi");
            address1.setState("New Delhi");
            address1.setEmployee(employee);

            List<Address> addresses = new ArrayList<Address>();
            addresses.add(address);
            addresses.add(address1);
            employee.setAddress(addresses);

            session.persist(employee);

            session.getTransaction().commit();

            // Query employees
            Query<Employee> employeeQuery = session.createQuery("from Employee", Employee.class);
            List<Employee> employees = employeeQuery.getResultList();
            for (Employee e : employees) {
                System.out.println("Employee: " + e.getEmployeeName());
                for (Address a : e.getAddress()) {
                    System.out.println("Address: " + a.getHouseNumber() + ", " + a.getCity() + ", " + a.getState());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
