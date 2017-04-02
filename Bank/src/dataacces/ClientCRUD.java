/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataacces;
import java.sql.Connection;
import java.sql.ResultSet;
import business.Clients;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author maria
 */
public class ClientCRUD {
    
    MySqlConnect conn = new MySqlConnect();
    PreparedStatement pst = null;
    ResultSet rs = null;
    //------------CREATE--------------------------------
    public String insert(Clients client)
    {
        Connection c=conn.getInstance();
        try{
            String queryStr = "Insert into clients (CNP,client_name,address,phone) values (?,?,?,?)";
            pst =c.prepareStatement(queryStr);
            pst.setString(1, client.getCNP());
            pst.setString(2, client.getNume());
            pst.setString(3, client.getAddress());
            pst.setString(4, client.getPhone());
            pst.executeUpdate();
        }catch(Exception ex)
        {
            System.out.print("Eroare");
            //return "Eroare";
        }
         return "Inserted successfully";
    }
    
    //---------------UPDATE--------------------
    public String update(String CNP, Clients client)
    {
        Connection c=conn.getInstance();
        try{
            String queryStr = "UPDATE clients SET client_name=?,address=?, phone=? where CNP=?";
            pst =c.prepareStatement(queryStr);
            pst.setString(1, client.getNume());
            pst.setString(2, client.getAddress());
            pst.setString(3, client.getPhone());
            pst.setString(4, CNP);
            pst.executeUpdate();
        }catch(Exception ex)
        {
            System.out.print("Eroare");
            return "Eroare";
        }
         return "Updated successfully";
    }
    //---------------DELETE--------------------
    public String delete(String deleteCNP)
    {
        Connection c=conn.getInstance();
        try{
            String queryStr = "DELETE FROM `clients` WHERE `clients`.`CNP` = ?";
            pst =c.prepareStatement(queryStr);
            pst.setString(1, deleteCNP);
            pst.executeUpdate();
        }catch(Exception ex)
        {
            System.out.print("Eroare");
            return "Eroare";
        }
         return "Deleted successfully";
    }
    //---------------LIST ONE OR MORE----------
    public DefaultTableModel getClients()
    {
        Connection c=conn.getInstance();
        try{
            //ArrayList<Clients> lista=new ArrayList<Clients>();
            DefaultTableModel lista = new DefaultTableModel(null, new Object[] { "CNP/CUI", "Name", "Address", "Phone" });
            String queryStr="Select * from clients";
            pst=c.prepareStatement(queryStr);
            rs=pst.executeQuery();
            while(rs.next())
            {
                lista.addRow(new Object[] {rs.getString("CNP"), rs.getString("client_name"),rs.getString("address"),rs.getString("phone")});
            }
            return lista;
        }catch(Exception ex)
        {
            System.out.print("Eroare");
            return null;
        }
    }
    
    public DefaultTableModel getClient(String CNP)
    {
        Connection c=conn.getInstance();
        try{
            DefaultTableModel client = new DefaultTableModel(null, new Object[] { "CNP/CUI", "Name", "Address", "Phone" });
            String queryStr="Select * from clients where CNP=?";
            pst=c.prepareStatement(queryStr);
            pst.setString(1, CNP);
            rs=pst.executeQuery();
            while(rs.next())
            {
                client.addRow(new Object[] {rs.getString("CNP"), rs.getString("client_name"),rs.getString("address"),rs.getString("phone")});
            }
            return client;
           
        }catch(Exception ex)
        {
            System.out.print("Eroare");
            return null;
        }
    }
}
