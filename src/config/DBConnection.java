package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String url = "jdbc:mysql://localhost:3306/blood_bank";
    private static final String userName = "root";
    private static final String password = "add_mysql_password";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, userName, password);
    }
}
