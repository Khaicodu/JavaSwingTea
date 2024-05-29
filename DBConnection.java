/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyTEA.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DBConnection {
    public static Connection getConnection() throws Exception{
        Connection connection = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=QLChidori;encrypt=false";
            String user = "sa";
            String pass = "1";
            connection = DriverManager.getConnection(url,user,pass);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return connection;
    }
      
    public static void closeConnection(Connection con){
        if(con!=null){
            try{
                con.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println(getConnection());
    }
            
}