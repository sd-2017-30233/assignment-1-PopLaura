/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataacces;

import dataacces.MySqlConnect;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author maria
 */
public class User {
    public static int IDactual;
    MySqlConnect conn = new MySqlConnect();
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void setIDactual(int IDactual) {
        User.IDactual = IDactual;
    }
    //-------------LOG-IN-----------------------------------
    public String login(String email, String passwords)
    {
        Connection c=conn.getInstance();
        try{
          String sql="select * from users u, user_roles ur, roles r where u.email=? AND u.passwords=? AND u.user_id=ur.user_id AND ur.role_id=r.role_id";
          pst=c.prepareStatement(sql);
          pst.setString(1, email);
          pst.setString(2, passwords);
          rs=pst.executeQuery();
          while(rs.next())
          {
              setIDactual(rs.getInt("user_id"));
              if(rs.getInt("role_id")==1) return "Admin";
              else return "Non-Admin";
          }
        }catch(Exception ex){
            System.out.print("Eroare");
            return "Eroare";
        }
        return "Username sau parola incorecta";
    }  
    //------------------------CREATE-------------------------------
    public String insert(String user_name, String email, String password, String address,String phone, String admin)
    {
        Connection c=conn.getInstance();
        try{
          CallableStatement callableStatement = c.prepareCall("{call ADD_USER(?,?,?,?,?,?)}");
          callableStatement.setString(1, user_name);
          callableStatement.setString(2, email);
          callableStatement.setString(3, password);
          callableStatement.setString(4, address);
          callableStatement.setString(5, phone);
          callableStatement.setString(6, admin);
          callableStatement.executeUpdate();
          callableStatement.close();
        }catch(Exception ex){
            System.out.print("Eroare");
            return "Eroare";
        }
        return "Creat cu succes";
    }
    //--------------------------DELETE-----------------------------
    public String delete(int user_id)
    {
        Connection c=conn.getInstance();
        try{
          String sql="DELETE FROM `users` WHERE `users`.`user_id` = ?";
          pst=c.prepareStatement(sql);
          pst.setInt(1, user_id);
          pst.executeUpdate();
        }catch(Exception ex){
            System.out.print("Eroare");
            return "Eroare";
        }
        return "Sters cu succes";
    } 
    //------------------------UPDATE---------------------------------
    public String update(int user_id,String user_name, String email, String password, String address,String phone, String admin)
    {
        Connection c=conn.getInstance();
        try{
          CallableStatement callableStatement = c.prepareCall("{call UPDATE_USER(?,?,?,?,?,?,?)}");
          callableStatement.setInt(1, user_id);
          callableStatement.setString(2, user_name);
          callableStatement.setString(3, email);
          callableStatement.setString(4, password);
          callableStatement.setString(5, address);
          callableStatement.setString(6, phone);
          callableStatement.setString(7, admin);
          callableStatement.executeQuery();
        }catch(Exception ex){
            System.out.print("Eroare");
            return "Eroare";
        }
        return "Actualizat cu succes";
    }
    //----------------------LIST ONE OR MORE USERS--------------------
    public DefaultTableModel getUsers()
    {
        Connection c=conn.getInstance();
        try{
          //ArrayList<String> lista=new ArrayList<String>();
          DefaultTableModel lista = new DefaultTableModel(null, new Object[] { "User Id", "Name", "Email", "Address", "Phone", "Admin or not" });
          String sql="SELECT u.user_id, u.user_name, u.email, u.address, u.phone, r.role_name FROM user_roles ur, users u, roles r WHERE u.user_id=ur.user_id AND ur.role_id=r.role_id";
          pst=c.prepareStatement(sql);
          rs=pst.executeQuery();
          while(rs.next())
            {
                lista.addRow(new Object[] {rs.getInt("user_id"), rs.getString("user_name"),rs.getString("email"),rs.getString("address"),rs.getString("phone"),rs.getString("role_name")});
                System.out.println("reusit");
            }
          return lista;
        }catch(Exception ex){
            System.out.print("Eroare");
            return null;
        }
    }
    public DefaultTableModel getUser(int user_id)
    {
        Connection c=conn.getInstance();
        try{
            //String user="";
            DefaultTableModel user = new DefaultTableModel(null, new Object[] { "User Id", "Name", "Email", "Address", "Phone", "Admin or not" });
            String sql="SELECT u.user_id, u.user_name, u.email, u.address, u.phone, r.role_name FROM user_roles ur, users u, roles r WHERE u.user_id=? AND ur.user_id=? AND ur.role_id=r.role_id";
            pst=c.prepareStatement(sql);
            pst.setInt(1, user_id);
            pst.setInt(2, user_id);
            rs=pst.executeQuery();
             while(rs.next())
            {
               user.addRow(new Object[] {rs.getInt("user_id"), rs.getString("user_name"),rs.getString("email"),rs.getString("address"),rs.getString("phone"),rs.getString("role_name")});
                return user;
            }
        }catch(Exception ex){
            System.out.print("Eroare");
            return null;
        }
        return null;
    }
    public DefaultTableModel Raport(int user_id)
    {
        Connection c=conn.getInstance();
        try{
            //String user="";
            DefaultTableModel user = new DefaultTableModel(null, new Object[] { "Operation "});
            System.out.println("1");
            String sql="SELECT descriere FROM `tranzactie` WHERE user_id=?";
            System.out.println("2");
            pst=c.prepareStatement(sql);
            System.out.println("3");
            pst.setInt(1, user_id);
            System.out.println("4");
            rs=pst.executeQuery();
            System.out.println("5");
             while(rs.next())
            {
                System.out.println("6");
               user.addRow(new Object[] {rs.getString("descriere")});
               System.out.println("7");
                return user;
            }
        }catch(Exception ex){
            System.out.print("Eroare");
            return null;
        }
        return null;
    }
}
