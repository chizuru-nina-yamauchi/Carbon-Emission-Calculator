package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
* This class should use the Singleton pattern to ensure
* only one instance of the connection is used throughout the application.
* Singleton pattern restricts the instantiation of a class and
* ensures that only one instance of the class exists in the Java Virtual Machine.
* The singleton class must provide a global access point to get the instance of the class.
*/


public class ConnectionFactory {
    private static ConnectionFactory instance; // Singleton pattern
    private static final String URL = "jdbc:postgresql://localhost:5432/carbon_emission";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    private Connection connection; // Singleton pattern

    // Constructor
    private ConnectionFactory() {
        // Private constructor to prevent instantiation from outside
    }

    // Singleton pattern
    public static ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }


    public Connection createConnection() {
        Connection connection = null;
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return connection;
    }

    public void printConnectionInfo(){
        try(Connection conn = createConnection()){
            if (conn != null) {
                System.out.println("Connected to the PostgreSQL server successfully.");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

}
