/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataacces;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author maria
 */
public class TransactionOperation {
  MySqlConnect conn = new MySqlConnect();
  PreparedStatement pst = null;
  ResultSet rs = null;  
  
  //-------------------------TRANSFER INTRE CONTURI-----------------------------------------
  public String TransferIntreConturi(String nr_cont_sursa, String nr_cont_destinatie, Float amount, String descriere, int user_id)
  {
      Connection c=conn.getInstance();
      try{
          CallableStatement callableStatement = c.prepareCall("{call TRANSFER_INTRE_CONTURI(?,?,?,?,?)}");
          callableStatement.setString(1, nr_cont_sursa);
          callableStatement.setString(2, nr_cont_destinatie);
          callableStatement.setFloat(3, amount);
          callableStatement.setString(4, descriere);
          callableStatement.setInt(5, user_id);
          callableStatement.executeQuery();
        }catch(Exception ex){
            System.out.print("Eroare");
            return "Eroare";
        }
      return "Tranzactie efectuata cu succes";
  }
  
  public DefaultTableModel TransactionHistory(String nr_cont)
    {
        Connection c=conn.getInstance();
        try{
            DefaultTableModel client = new DefaultTableModel(null, new Object[] { "Transaction ", "Source", "Destination", "Operation Id", "User_ID", "Amount", "Data", "Time", "Description" });
            String queryStr="Select * from tranzactie where cont_sursa=? or cont_destinatie=?";
            pst=c.prepareStatement(queryStr);
            pst.setString(1, nr_cont);
            pst.setString(2, nr_cont);
            rs=pst.executeQuery();
            while(rs.next())
            {
                client.addRow(new Object[] {rs.getInt("id_tranz"), rs.getString("cont_sursa"),rs.getString("cont_destinatie"),rs.getInt("id_op"), rs.getInt("user_id"), rs.getFloat("amount"),rs.getDate("dataa"),rs.getTime("timp"), rs.getString("descriere")});
            }
            return client;
           
        }catch(Exception ex)
        {
            System.out.print("Eroare");
            return null;
        }
    }
  
  
    
}
