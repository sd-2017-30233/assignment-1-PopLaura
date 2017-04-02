/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;
import java.sql.Timestamp;
import java.util.Date;
/**
 *
 * @author maria
 */
public class Account {
    public String nrCont;
    public String clientID;
    public Date data;
    public Float amount;
    
    public Account()
    {
        
    }
    
    public Account(String nrCont, String clientID, Date data, Float amount)
    {
        this.setNrCont(nrCont);
        this.setClientID(clientID);
        this.setData(data);
        this.setAmount(amount);
    }
    //----------NUMAR CONT--------------
    public void setNrCont(String nrCont)
    {
        this.nrCont=nrCont;
    }
    public String getNrCont()
    {
        return this.nrCont;
    }
    //---------ID Client----------------
    public void setClientID(String clientID)
    {
        this.clientID=clientID;
    }
    public String getClientID()
    {
        return this.clientID;
    }
    //---------------DATA CREARII----------
    public void setData(Date data) {
        this.data = data;
    }

    public Date getData() {
        return data;
    }
    //--------------AMOUNT-------------------
    public void setAmount(Float amount) {
        this.amount = amount;
    }
    public Float getAmount() {
        return amount;
    }
    
}
