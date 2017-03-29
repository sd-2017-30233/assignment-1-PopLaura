/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataacces;
import business.Account;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author maria
 */
public class AccountCRUD {
    MySqlConnect conn = new MySqlConnect();
    PreparedStatement pst = null;
    ResultSet rs = null;
    //-----------CREATE-----------------
    public String insert(Account account)
    {
        Connection c=conn.getInstance();
        try{
            String sql="insert into account (nr_cont, client_id, dataa, amount) values (?,?,?,?)";
            pst=c.prepareStatement(sql);
            pst.setString(1, account.getNrCont());
            pst.setString(2, account.getClientID());
            pst.setTimestamp(3, new Timestamp(account.getData().getTime()));
            pst.setFloat(4, account.getAmount());
            pst.executeUpdate();
        }catch(Exception ex){
            System.out.print("Eroare");
            return "Eroare";
        }
        return "Account created!";
    }
    //----------UPDATE------------------
    public String update(String nr_cont, Account account)
    {
        Connection c=conn.getInstance();
        try{
            String queryStr = "UPDATE account SET client_id=?, dataa=?, amount=? where nr_cont=?";
            pst =c.prepareStatement(queryStr);
            pst.setString(1, account.getClientID());
            pst.setTimestamp(2, new Timestamp(account.getData().getTime()));
            pst.setFloat(3, account.getAmount());
            pst.setString(4, nr_cont);
            pst.executeUpdate();
        }catch(Exception ex)
        {
            System.out.print("Eroare");
            return "Eroare";
        }
         return "Updated successfully";
    }
    //----------DELETE------------------
    public String delete(String nr_cont)
    {
        Connection c=conn.getInstance();
        try{
            String queryStr = "DELETE FROM `account` WHERE `account`.`nr_cont` = ?";
            pst =c.prepareStatement(queryStr);
            pst.setString(1, nr_cont);
            pst.executeUpdate();
        }catch(Exception ex)
        {
            System.out.print("Eroare");
            return "Eroare";
        }
         return "Deleted successfully";
    }
    //----------LIST ONE OR MORE--------
    
    public DefaultTableModel getAccounts()
    {
        Connection c=conn.getInstance();
        try{
            //ArrayList<Account> lista=new ArrayList<Account>();
            DefaultTableModel lista = new DefaultTableModel(null, new Object[] { "Account Number", "Id Client", "Data", "Amount" });
            String queryStr="Select * from account";
            pst=c.prepareStatement(queryStr);
            
            rs=pst.executeQuery();
            while(rs.next())
            {
                lista.addRow(new Object[] {rs.getString("nr_cont"), rs.getString("client_id"), rs.getDate("dataa"), rs.getFloat("amount")});
            }
            return lista;
        }catch(Exception ex)
        {
            System.out.print("Eroare");
            return null;
        }
    }
    
    public DefaultTableModel getAccount(String nr_cont)
    {
        Connection c=conn.getInstance();
        try{
            String queryStr="Select * from account where nr_cont=?";
            pst=c.prepareStatement(queryStr);
            pst.setString(1, nr_cont);
            rs=pst.executeQuery();
            DefaultTableModel acc = new DefaultTableModel(null, new Object[] { "Account Number", "Id Client", "Data", "Amount" });
            while(rs.next())
            {
                acc.addRow(new Object[] {rs.getString("nr_cont"), rs.getString("client_id"), rs.getDate("dataa"), rs.getFloat("amount")});
            }
            return acc;
           
        }catch(Exception ex)
        {
            System.out.print("Eroare");
            return null;
        }
    }
}
