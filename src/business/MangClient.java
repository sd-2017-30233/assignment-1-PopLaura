/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;
import dataacces.ClientCRUD;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author maria
 */
public class MangClient {
    ClientCRUD crud=new ClientCRUD();
    public String addC(String CNP, String nume, String address, String phone)
    {
        String validate=ValidareClient.validateClient(CNP);
        if(validate.equals("Valid"))
        {
            Clients c=new Clients(CNP, nume, address, phone);
            return crud.insert(c);
        }
        else return validate;
    }
    
    public String deleteC(String CNP)
    {
        String validate=ValidareClient.validateClient(CNP);
        if(validate.equals("Valid"))
        {
            return crud.delete(CNP);
        }
        else return "CNP invalid";
    }
    public String updateC(String CNP, String nume, String address, String phone)
    {
        String validate=ValidareClient.validateClient(CNP);
        if(validate.equals("Valid"))
        {
            Clients c=new Clients(CNP, nume, address, phone);
            return crud.update(CNP, c);
        }
        return validate;
    }
    public DefaultTableModel getC()
    {
        return crud.getClients();
    }
    
    public DefaultTableModel getCs(String CNP)
    {
        //String validate=ValidareClient.validateClient(CNP);
        //if(validate.equals("Valid"))
        //{
            return crud.getClient(CNP);
        //}
        //else return null;
    }
}
