/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laporanstres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author acer
 */
public class connect_db {
     private static Connection mysqlconfig;
    public static Connection configDB() throws SQLException {
        try {
            String url="jdbc:mysql://localhost:3306/breado";
            String user="root";
            String pass="";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            mysqlconfig=DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.err.println("Koneksi Gagal "+e.getMessage());   
        } return mysqlconfig;
    }
}
