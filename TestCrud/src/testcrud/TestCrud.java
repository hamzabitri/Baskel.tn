/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcrud;

import Entities.*;
import Service.*;
import Utils.DataBase;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hamza
 */
public class TestCrud {
public static void main(String[] args) {
          ServiceCommande serC = new ServiceCommande();
          ServiceLigneCommande serLC = new ServiceLigneCommande();
        
        Commande c1 = new Commande(2, "Validée");
        Commande c2 = new Commande(5, "En cours de traitement");
        Commande c3 = new Commande(8, "A traiter");
        
        LigneCommande lc1 = new LigneCommande(2, 255);
        LigneCommande lc2 = new LigneCommande(5,30);
        LigneCommande lc3 = new LigneCommande(8, 12);
        
        try {
//Crud Commande        
         
           serC.ajouter1(c1);
           serC.ajouter1(c2);
           serC.ajouter1(c3);
           boolean varC=serC.delete(c3);
           System.out.println(varC);
           boolean varC2=serC.update(c1,"A traiter");
           System.out.println(varC2);
           Commande c = serC.getById(13);
           System.out.println(c);
           List<Commande> listC = serC.readAll();
           System.out.println(listC);
           List<Commande> listC2 = serC.getTrier();
           System.out.println(listC2);

/*********************************************************************************************************************************************************/
//Crud Ligne Commande        

        serLC.ajouter1(lc1);
        serLC.ajouter1(lc2);
        serLC.ajouter1(lc3);
        boolean varLC=serLC.delete(lc3);
        System.out.println(varLC);
        LigneCommande lc = serLC.getById(7);
        System.out.println(c);
        List<LigneCommande> listLC = serLC.readAll();
        System.out.println(listLC);
        List<LigneCommande> listLC2 = serLC.getTrier();
        System.out.println(listLC2);

} catch (SQLException ex) {
            System.out.println(ex);
        }
}
    
}
