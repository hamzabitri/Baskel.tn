/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Entities.Article;
import Service.ServiceArticle;
import Utils.DataBase;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author House
 */
public class GestionDesArticles {
    
    public static void main(String[] args) {
        ServiceArticle ser = new ServiceArticle();
        
        Article p1 = new Article("Ons", "Ksila", 5);
        Article p2 = new Article("Hamza", "Bitri",3);
        Article p3 = new Article("Mariem", "Chtoutrou", 4);
        
        try {
//         
            ser.ajouter1(p2);
            ser.ajouter1(p1);
            ser.ajouter1(p3);
            ser.delete(p3);
            ser.update(p2,"Omar");
            //ser.ajouter(p3);
            Article p = ser.getById(21);
            
            System.out.println(p);
            List<Article> list = ser.readAll();
            List<Article> list2 = ser.getTrier();
            
            
            System.out.println(list);
            System.out.println(list2);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
