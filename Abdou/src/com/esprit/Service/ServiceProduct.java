/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Product;
import com.esprit.IService.IService;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import com.esprit.Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author House
 */
public abstract class ServiceProduct implements IService<Product> {

    private Connection con;
    private Statement ste;

    public ServiceProduct() {
        con = DataBase.getInstance().getConnection();

    }
/*
    @Override
    public void ajouter(Product t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `baskel`.`product` (`id_p`, `name`, `price`, `category`) VALUES (NULL, '" + t.getId()"', '"+ t.getName() + "', '" + t.getPrice() + "', '" + t.getCategory() + "');";
        ste.executeUpdate(requeteInsert);
    }*/
    public void ajouter1(Product p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `baskel`.`product` ( `id_p`,`name`, `price`, `category`) VALUES (?, ?, ?, ?);");
    pre.setInt(1, p.getId());
    pre.setString(2, p.getName());
    pre.setFloat(3, p.getPrice());
    pre.setInt(4, p.getCategory());
    pre.executeUpdate();
    }
            

    @Override
    public boolean delete(Product t) throws SQLException {
          PreparedStatement pre=con.prepareStatement("DELETE FROM product WHERE id_p=?");
          boolean rowDeleted=false;
          
    pre.setInt(1, t.getId());
   
    pre.executeUpdate();
    rowDeleted=pre.executeUpdate() >0 ;
        return rowDeleted;
        
    }

    @Override
    public boolean update(Product p) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE product SET name = ?, price =? , category=? WHERE id_p=?");
        boolean rowDeleted=false;
            pre.setString(1, p.getName());
    pre.setFloat(2, p.getPrice());
    pre.setInt(3, p.getCategory());
    pre.setInt(4, p.getId());
    pre.executeUpdate();
    rowDeleted=pre.executeUpdate() >0 ;
     return rowDeleted;
        
    }

    @Override
    public List<Product> readAll() throws SQLException {
    List<Product> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM product");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String name=rs.getString(2);
               float price=rs.getFloat(3);
               int category=rs.getInt(4);
               Product p=new Product(id, name, price, category);
     arr.add(p);
     }
    return arr;
    }
    @Override
    public List<Product> getTrier() throws SQLException {
    List<Product> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from product ORDER BY id_p DESC");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String name=rs.getString(2);
               float price=rs.getFloat(3);
               int category=rs.getInt(4);
               Product p=new Product(id, name, price,category);
     arr.add(p);
     }
    return arr;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
 public Product getById(int id) {
          Product a = null;
         String requete = " select* from Product  where id_p='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            ResultSet res=ste.executeQuery(requete);
            if (res.next())
            {a=new Product(res.getInt(1),res.getString(2),res.getFloat(3),res.getInt(4));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
    /**
     *
     * @param t
     * @return
     * @throws SQLException
     */
    /*@Override
    public boolean delete(Product t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Product t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
