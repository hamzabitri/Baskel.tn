/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.test;

import com.esprit.Entite.Event;
import com.esprit.Entite.Categorie;
import com.esprit.Service.ServiceEvent;
import com.esprit.Service.ServiceCategorie;
import com.esprit.Utils.DataBase;
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
        ServiceEvent ser = new ServiceEvent();
        ServiceCategorie ser1 = new ServiceCategorie();
        
        Event p1 = new Event("Edition1", "Best", "event", "mars", "aout", "marsa");
        Event p2 = new Event("Edition2", "Best", "event", "mai", "sept", "gammarth");
        Categorie c1 = new Categorie("event1", "lien");
        Categorie c2 = new Categorie("event2", "lien2");
        
        try {
            Event p = ser.getById(60);
            Event c = ser.getByName("t");
            
            System.out.println(p);
            System.out.println(c);
            
            ser.ajouter1(p2);
            ser.ajouter(p1);
            
            ser1.ajouter(c1);
            ser1.ajouter(c2);
            
            boolean var1=ser1.delete(c1);
            System.out.println(var1);
            
            boolean var2=ser.update(p1,"update");
            System.out.println(var2);
          
            boolean var3=ser1.update(c2,"update");
            System.out.println(var3);
            
            boolean var=ser.delete(p2);
            System.out.println(var);
            
            List<Event> list = ser.readAll();
            System.out.println(list);
            List<Categorie> list1 = ser1.readAll();
            System.out.println(list1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
