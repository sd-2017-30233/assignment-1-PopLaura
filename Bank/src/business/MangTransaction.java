/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;
import dataacces.TransactionOperation;
/**
 *
 * @author maria
 */
public class MangTransaction {
    
    TransactionOperation tranzactie=new TransactionOperation();

    //-------------------------TRANSFER INTRE CONTURI-----------------------------------------
    public String Tranzactie(String nr_cont_sursa, String nr_cont_destinatie, Float amount, String descriere, int user_id)
    {
       //Validare
        String validare=tranzactie.TransferIntreConturi(nr_cont_sursa, nr_cont_destinatie, amount, descriere, user_id);
        return validare;
    }
    //-------------------------GAZ-----------------------------------------
    public String PayGas(String nr_cont_sursa, String payG, Float amount, String descriere, int user_id)
    {
       //Validare
        String validare=tranzactie.TransferIntreConturi(nr_cont_sursa, payG, amount, "Factura gaz", user_id);
        return validare;
    }
    //-------------------------APA-----------------------------------------
    public String PayWater(String nr_cont_sursa, String payW, Float amount, String descriere, int user_id)
    {
       //Validare
        String validare=tranzactie.TransferIntreConturi(nr_cont_sursa, payW, amount, "Factura apa", user_id);
        return validare;
    }
    //-------------------------CUERENT ELECTRIC----------------------------
    public String PayElectric(String nr_cont_sursa, String payE, Float amount, String descriere, int user_id)
    {
       //Validare
        String validare=tranzactie.TransferIntreConturi(nr_cont_sursa, payE, amount, "Factura CURENT ELECTRIC", user_id);
        return validare;
    }
    
}
