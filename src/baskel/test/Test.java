/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baskel.test;

import baskel.Entite.Produit;

import baskel.Service.ServiceProduit;

import baskel.Utils.DataBase;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author House
 */
public class Test {
    
    public static void main(String[] args) {
        ServiceProduit ser = new ServiceProduit();

        
        //Event p1 = new Event("Edition1", "Best", "event","","", "marsa");
        //Event p2 = new Event("Edition2", "Best", "event","","", "gammarth");

        try {
            Produit p = ser.getById(60);
            Produit c = ser.getByName("t");           
            System.out.println(p);
            System.out.println(c);     
            List <Produit> liste = ser.readAll();
//            ser.ajouter1(p2);
//            ser.ajouter(p1);
//            boolean var2=ser.update(p1,"update");
//            System.out.println(var2);
//            boolean var=ser.delete(p2);
//            System.out.println(var);         
            List<Produit> list = ser.readAll();
            System.out.println(list);
       
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
