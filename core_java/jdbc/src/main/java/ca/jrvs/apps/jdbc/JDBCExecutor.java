package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCExecutor {

    public static void main(String[] args) {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hplussport",
                "postgres", "password");

        try {
            Connection connection = dcm.getConnection();
            CustomerDAO customerDao = new CustomerDAO(connection);
            customerDao.findAllSorted(20).forEach(System.out::println);
            System.out.println("\n");
            System.out.println("Page");
            for (int i = 1; i < 3; i++) {
                customerDao.findAllPaged(10, i).forEach(System.out::println);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
