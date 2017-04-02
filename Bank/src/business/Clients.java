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
public class Clients {
    public String CNP;
    public String nume;
    public String address;
    public String phone;
    
    public Clients()
    {
        
    }
    
    public Clients(String CNP, String nume, String address, String phone)
    {
        this.setCNP(CNP);
        this.setNume(nume);
        this.setAddress(address);
        this.setPhone(phone);
    }
    //public 
    //--------CNP------------
    public void setCNP(String CNP)
    {
        this.CNP=CNP;
    }
    public String getCNP()
    {
        return this.CNP;
    }
    //----------NUME----------
     public void setNume(String nume)
    {
        this.nume=nume;
    }
    public String getNume()
    {
        return this.nume;
    }
    //----------ADRESA--------
     public void setAddress(String address)
    {
        this.address=address;
    }
    public String getAddress()
    {
        return this.address;
    }
    //----------PHONE---------
     public void setPhone(String phone)
    {
        this.phone=phone;
    }
    public String getPhone()
    {
        return this.phone;
    }
}
