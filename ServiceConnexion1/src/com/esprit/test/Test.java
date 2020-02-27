/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.test;

import com.esprit.Entite.Personne;
import com.esprit.Service.ServicePersonne;
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
        ServicePersonne ser = new ServicePersonne();
        
        Personne p1 = new Personne("sana", "ben fadhel", 10);
        Personne p2 = new Personne("Karray", "Gargouri", 10);
        
        try {
//         
            ser.ajouter1(p2);
            ser.ajouter(p1);
            List<Personne> list = ser.readAll();
            System.out.println(list);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
