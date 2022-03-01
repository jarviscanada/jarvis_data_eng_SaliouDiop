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
            customer.setFirstName("John");
            customer.setLastName("Doe");
            customer.setEmail("john.doe@abc.com");
            customer.setPhone("(123) 456-7890");
            customer.setAddress("123 Main St");
            customer.setCity("Anytown");
            customer.setState("CA");
            customer.setZipCode("12345");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
