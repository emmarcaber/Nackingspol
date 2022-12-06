/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;
import java.sql.*;

/**
 *
 * @author Learning Lab
 */
public class DBConnect {
    
    public static Connection conn() {
        Connection conn = null;
        
        try {
            String url = "jdbc:mysql://localhost:3306/nackingspol";
            String user = "root";
            String pass = "";
            
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return conn;
    }
}
