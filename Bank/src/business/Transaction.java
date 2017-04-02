/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author maria
 */
public class Transaction {
    String nr_cont_sursa;
    String nr_cont_destinatie;
    Float amount;
    String descriere;
    int user_id;

    public Transaction() {
        
    }
    public Transaction(String nr_cont_sursa, String nr_cont_destinatie, Float amount, String descriere, int user_id) {
        this.nr_cont_sursa = nr_cont_sursa;
        this.nr_cont_destinatie = nr_cont_destinatie;
        this.amount = amount;
        this.descriere = descriere;
        this.user_id = user_id;
    }

    
    
    public String getNr_cont_sursa() {
        return nr_cont_sursa;
    }

    public String getNr_cont_destinatie() {
        return nr_cont_destinatie;
    }

    public Float getAmount() {
        return amount;
    }

    public String getDescriere() {
        return descriere;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setNr_cont_sursa(String nr_cont_sursa) {
        this.nr_cont_sursa = nr_cont_sursa;
    }

    public void setNr_cont_destinatie(String nr_cont_destinatie) {
        this.nr_cont_destinatie = nr_cont_destinatie;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    
    
}
