package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExecutor {

    public static void main(String[] args) {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hplussport",
                "postgres", "password");

        try {
            Connection connection = dcm.getConnection();
            CustomerDAO customerDao = new CustomerDAO(connection);
            Customer customer = new Customer();
            customer.setFirstName("Momo");
            customer.setLastName("Fall");
            customer.setEmail("mom.fall@abc.com");
            customer.setPhone("123-456-7890");
            customer.setAddress("232 Main St");
            customer.setCity("Toronto");
            customer.setState("ON");
            customer.setZipCode("M5A2B3");

            customerDao.create(customer);
            System.out.println("Customer created: " + customer);

            customerDao.findById(customer.getId());
            System.out.println("Customer found: " + customer);
            // delete customer
            customerDao.delete(customer.getId());
            System.out.println("Customer deleted: " + customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
