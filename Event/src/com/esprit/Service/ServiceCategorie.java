/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Categorie;
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
public class ServiceCategorie implements IService<Categorie> {

    private Connection con;
    private Statement ste;
    private ResultSet res ;
    public ServiceCategorie() {
        con = DataBase.getInstance().getConnection();

    }


    @Override
    public void ajouter(Categorie t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO categorie (`idC`, `nomC`, `lienC`) VALUES (NULL, '" + t.getNom() + "', '" + t.getLien() + "');";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(Categorie p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `baskel`.`categorie` ( `idC`, `nomC`, `lienC` ) VALUES ( ?, ?, ?);");
    pre.setString(1, p.getNom());
    pre.setString(2, p.getLien());
  
    pre.executeUpdate();
    }
            

    @Override
    public boolean delete(Categorie t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM categorie WHERE nomC='" + t.getNom() + "'; ");
        int a=pre.executeUpdate();
        return a!=0;
    }
    @Override
    public boolean update(Categorie t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categorie> readAll() throws SQLException {
    List<Categorie> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from categorie");
     while (rs.next()) {                
               int idC=rs.getInt(1);
               String nomC=rs.getString("nom");
               String lienC=rs.getString("lien");
              
             
               Categorie c=new Categorie(idC, nomC, lienC);
               
     arr.add(c);
     }
    return arr;
    }
    
     public boolean update(Categorie x,String nomC) throws SQLException {
               PreparedStatement pre=con.prepareStatement("UPDATE categorie  SET nomC='"+ nomC  +"' WHERE nomC='" + x.getNom() +"';");
               int a=pre.executeUpdate();
               return(a!=0);
    }
     
       public Categorie getById(int idC) {
          Categorie a = null;
         String requete = " select* from categorie  where idC='"+idC+"'" ;
        try {
           
            ste = con.createStatement();
            ResultSet res = ste.executeQuery(requete);
            if (res.next())
            {
                a=new Categorie(res.getInt(1),res.getString(2),res.getString(3));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
       
              public Categorie getByName(String n) {
          Categorie a = null;
         String requete = " select* from categorie where (nomC like '"+n+"%')" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next()){
             a=new Categorie(res.getInt(1),res.getString(2),res.getString(3));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
       
           @Override
    public Categorie getByName(Categorie f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       
   @Override
    public Categorie getById(Categorie f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}