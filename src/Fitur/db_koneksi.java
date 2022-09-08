package Fitur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db_koneksi {
   
    private static Connection mysqlconfig;
    public static Connection configDB()throws SQLException {
        
        try {
            String url="jdbc:mysql://localhost:3306/db_baker";
            String user="root";
            String pass="";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            mysqlconfig= DriverManager.getConnection(url, user, pass);
            
        } catch (Exception e) {
            System.err.println("koneksiDB "+e.getMessage());
        }
        return mysqlconfig;
    }
       
} 
