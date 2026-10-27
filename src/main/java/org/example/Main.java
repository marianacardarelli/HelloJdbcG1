package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() throws SQLException {
        String url = "jdbc:mysql://localhost:3309/ad";
        String username = "user";
        String password = "pwd";
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        }
}
