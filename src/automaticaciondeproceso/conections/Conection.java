/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automaticaciondeproceso.conections;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author wwwca
 */
public class Conection {
    Connection con;
    public Conection() throws SQLException{
     con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3309/sistemaumg","root","123/umg/321");
    }
    public Connection conexion(){
        try{
             Class.forName("com.mysql.jdbc.Driver");
             con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3309/sistemaumg","root","123/umg/321");
        }catch(ClassNotFoundException | SQLException e){
            System.out.print(e.getMessage());
          
        }
        return con;
    }
   public void insert(String str){
       try{
           Statement stmt=(Statement) con.createStatement();
           stmt.executeUpdate(str);
       }catch(Exception e){
       
       }
   }
   public ResultSet select(String str){
       try{
           Statement stmt=(Statement) con.createStatement();
           ResultSet rs = stmt.executeQuery(str); 
           return rs;
         
       }catch(Exception e){
       
       }
        return null;
   }
    
    public void closeConection() throws SQLException{
            con.close();
    }
}
