package org.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcUtils {

    private static final String DB_URL = "jdbc:h2:~/test;AUTO_SERVER=TRUE";

    private static Connection connection;

    @Bean(value = "connection")
    public static boolean createConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, "angelina", "1234");
            return true;
        } catch (Exception ex) {
            System.out.println("Error occurred while connection to database: " + ex.getMessage());
        }
        return false;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (Exception ex) {
            System.out.println("Error occurred while closing connection to database: " + ex.getMessage());
        }
    }
}

