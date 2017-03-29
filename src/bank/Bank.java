/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;
import dataacces.*;
import business.*;
import java.util.ArrayList;
/**
 *
 * @author maria
 */
public class Bank {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //MangClient cr=new MangClient();
        //cr.updateC("2904765657", "Andi Dica", "p", "9999");
        //MangTransaction tr=new MangTransaction();
        //tr.Tranzactie("57662305654", "65652104554",10f, "Proba", 1);
        LOGIN login=new LOGIN();
        login.setVisible(true);
        //Clients c=new Clients("2904765657", "Andi Dica", "Porii", "92836487");
        //ClientCRUD cr=new ClientCRUD();
        //User u=new User();
        //ArrayList<String> user=new ArrayList<String>();
        //user=u.getUsers();
        //String users=u.getUser(1);
        //System.out.println(users);
        //TransactionOperation tr=new TransactionOperation();
        //tr.TransferIntreConturi("54459795435", "57662305654",10f, "Proba", 1);
        /*for(int i = 0; i < user.size(); i++) {   
    System.out.println(user.get(i));
}*/
        /*ArrayList<Clients> clientArrayList=c.getClients();
        for(int i = 0; i < clientArrayList.size(); i++) {   
    System.out.print(clientArrayList.get(i).getCNP());
    System.out.print(clientArrayList.get(i).getNume());
    System.out.print(clientArrayList.get(i).getAddress());
    System.out.println(clientArrayList.get(i).getPhone());
}
        System.out.println();
        // TODO code application logic here
        AccountCRUD ac=new AccountCRUD();
        ArrayList<Account> accountlientArrayList=ac.getAccounts();
        for(int i = 0; i < clientArrayList.size(); i++) {   
    System.out.print(accountlientArrayList.get(i).getNrCont()+"   ");
    System.out.print(accountlientArrayList.get(i).getClientID()+"   ");
    System.out.print(accountlientArrayList.get(i).getData()+"   ");
    System.out.println(accountlientArrayList.get(i).getAmount()+"   ");
}*/
        //u.insert("Pop Anca", "popanca@yahoo.com", "lala", "Cluj-Napoca", "0786222079", "admin");
        //cr.insert(c);
        //cr.update("2904765657", c);
        
    }
    
}
