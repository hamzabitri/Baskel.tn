/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.test;

import com.esprit.Entite.Category;
import com.esprit.Entite.Product;
import com.esprit.Service.ServiceCategory;
import com.esprit.Service.ServiceProduct;
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
    
    public static void main(String[] args) throws SQLException {
        ServiceProduct ser = new ServiceProduct() {
            @Override
            public void ajouter(Product t) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            
            }

            
            public Product getById(Product f) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        ServiceCategory ser1;
        ser1 = new ServiceCategory() {
            @Override
            public void ajouter(Category t) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            

            
            @Override
            public Category getById(int f) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            
            
            
        };
       Product p1 = new Product(3,"enti ", 96, 69);
        Product p2 = new Product(8,"ho", 6, 10);
        Product p3 = new Product(4,"chtourouu", 12, 12);
        Category c1=new Category(2,"loula updated");
        Category c2=new Category(14,"second");
        
        //
        //ser.ajouter1(p2);
        //ser.ajouter1(p3);
        //ser.delete(p2);
        // ser.update(p1);
        List<Product> list = ser.readAll();
        //ser1.ajouter1(c1);
        //ser1.ajouter1(c2);
        //ser1.ajouter1(c2);
        List<Category> list1 = ser1.readAll();
        //List<Product> list2 =ser.getTrier();
        //List<Category> list1 =ser1.getTrier();
        Product pp=ser.getById(3);
        //ser.getByany(p3);
        System.out.println(pp);
                System.out.println(list);

    }
    
}
