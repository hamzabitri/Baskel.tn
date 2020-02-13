/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Category;
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
public abstract class ServiceCategory implements IService<Category> {

    private Connection con;
    private Statement ste;

    public ServiceCategory() {
        con = DataBase.getInstance().getConnection();

    }
/*
    @Override
    public void ajouter(Category t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `baskel`.`Category` (`id_p`, `name`, `price`, `category`) VALUES (NULL, '" + t.getId()"', '"+ t.getName() + "', '" + t.getPrice() + "', '" + t.getCategory() + "');";
        ste.executeUpdate(requeteInsert);
    }*/
    public void ajouter1(Category p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `baskel`.`category` ( `id_cp`,`name`) VALUES (?, ?);");
    pre.setInt(1, p.getId());
    pre.setString(2, p.getName());
    pre.executeUpdate();
    }
            

    @Override
    public boolean delete(Category t) throws SQLException {
          PreparedStatement pre=con.prepareStatement("DELETE FROM category WHERE id_cp=?");
          boolean rowDeleted=false;
          
    pre.setInt(1, t.getId());
   
    pre.executeUpdate();
    rowDeleted=pre.executeUpdate() >0 ;
        return rowDeleted;
        
    }

    @Override
    public boolean update(Category p) throws SQLException {
        PreparedStatement pre=con.prepareStatement("UPDATE category SET name = ? WHERE id_cp=?");
        boolean rowDeleted=false;
            pre.setString(1, p.getName());

    pre.setInt(2, p.getId());
    pre.executeUpdate();
    rowDeleted=pre.executeUpdate() >0 ;
     return rowDeleted;
        
    }

    @Override
    public List<Category> readAll() throws SQLException {
    List<Category> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM category");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String name=rs.getString(2);
               Category p=new Category(id, name);
     arr.add(p);
     }
    return arr;
    }
@Override
    public List<Category> getTrier() throws SQLException {
    List<Category> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from category ORDER BY id_cp DESC");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String name=rs.getString(2);
               
               Category c=new Category(id, name);
     arr.add(c);
     }
    return arr;
    }
    
    @Override
     public Category getById(int id) {
          Category a = null;
         String requete = " select* from category  where id_cp='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            ResultSet res=ste.executeQuery(requete);
            if (res.next())
            {a=new Category(res.getInt(1),res.getString(2));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategory.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean delete(Category t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Category t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
