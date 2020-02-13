/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidv;
import pidv.Entite.User;
import Service.ServiceUser;
import connexion.DataBase;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author azizs
 */
public class PIDV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServiceUser ser = new ServiceUser();
        
        User p1 = new User("aziz", "Sayeb","aziz","aziz","admin");
        User p2 = new User("hamza", "Bitri", "Hamza","ssss","client");
        User p3=new User("amine","ktari","kk","test","admin");
        
        try {
//         
           
            ser.ajouter1(p1);
            ser.ajouter1(p2);
            ser.ajouter1(p3);
            User p = ser.getById(48);
            User c = ser.getByName("az");
            
            System.out.println(p);
            System.out.println(c);
            List<User> list = ser.readAll();
            List<User> liste=ser.getTrier() ;
            System.out.println(list);
            System.out.println(liste);
          //  boolean var= ser.delete(p3);
          //  boolean var2= ser.update(p2, "Aziz");
          //  System.out.println(var);
           // System.out.println(var2);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
        // TODO code application logic here
    }
    

