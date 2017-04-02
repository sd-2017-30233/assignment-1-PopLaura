/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;
import dataacces.AccountCRUD;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author maria
 */
public class MangAccount {
    AccountCRUD crud=new AccountCRUD();
    public String addA(String nrCont, String clientID, Date data, Float amount)
    {
        String validate=ValidareClient.validateClient(clientID);
        if(validate.equals("Valid"))
        {
            Account c=new Account(nrCont, clientID, data, amount);
            return crud.insert(c);
        }
        else return validate;
    }
    
    public String deleteA(String nrCont)
    {
            return crud.delete(nrCont);
    }
    
    public String updateA(String nrCont, String clientID, Date data, Float amount)
    {
        String validateNRCONT=ValidareAccount.validateAccunt(amount);
        String validateCNP=ValidareClient.validateClient(clientID);
        if(validateCNP.equals("Valid") && validateNRCONT.equals("Valid") )
        {
            Account ac=new Account(nrCont, clientID, data, amount);
            return crud.update(nrCont, ac);
        }
        return validateCNP;
    }
    public DefaultTableModel getA()
    {
        return crud.getAccounts();
    }
    
    public DefaultTableModel getCs(String nrCont)
    {
            return crud.getAccount(nrCont);
    }
}
