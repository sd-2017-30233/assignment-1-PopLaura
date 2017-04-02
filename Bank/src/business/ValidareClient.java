/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

/**
 *
 * @author maria
 */
public class ValidareClient {
    public static String validateClient(String CNP)
    {
        if(parseLong(CNP)>=1000000000000L && parseLong(CNP)<=9999999999999L)
            return "Valid";
        else return "Non-valid";
    }
    
}
