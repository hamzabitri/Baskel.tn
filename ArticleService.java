/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Article;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.*;
/**
 *
 * @author onsks
 */
public class ArticleService {
     private final Connection con;
    private Statement ste;
        private ResultSet res ;


    public  ArticleService() throws SQLException {
    con = DataBase.getInstance().getCnx();

    }

     
    public void ajouter(Article t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `article` (`idarticle`, `idclient`, `titre`,`description`,`imageA`) VALUES (NULL, ' + t.getIdc()+ ', '" + t.getTitre()+ "','" + t.getDescription()+ "','" + t.getImageA()+ "');";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(Article p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `article` (`idclient`,`titre`,`description`,`imageA`) VALUES (?,?,?,?);");
    pre.setInt(1, p.getIdc());
    pre.setString(2, p.getTitre());
    pre.setString(3, p.getDescription());
    pre.setString(4, p.getImageA());
    pre.executeUpdate();
    }
            

     
    public boolean delete(Article t) throws SQLException {
           
               PreparedStatement pre=con.prepareStatement("DELETE FROM `article` WHERE idarticle='"+ t.getIda()+"' ;");
              // pre.executeUpdate();       
                int a=pre.executeUpdate();
                return(a!=0);
    }

     
    public boolean update(Article t,String titre) throws SQLException {
               PreparedStatement pre=con.prepareStatement("UPDATE `article`  SET titre='"+ titre  +"' where idclient='"+ t.getIdc() +"' ;");
               int a=pre.executeUpdate();
               return(a!=0);
    }

   
     
    public List<Article> readAll() throws SQLException {
    List<Article> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from article");
     while (rs.next()) {                
               int ida=rs.getInt(1);
               int idc=rs.getInt(2);
               String titre=rs.getString(3);
               String description=rs.getString(4);
               String img=rs.getString(5);
               Article p=new Article(ida, idc, titre,description,img);
     arr.add(p);
     }
    return arr;
   }
 
     
       
    public List<Article> getTrier() throws SQLException {
    List<Article> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from article ORDER BY idclient DESC");
     while (rs.next()) {                
               int ida=rs.getInt(1);
               int idc=rs.getInt(2);
               String titre=rs.getString(3);
               String description=rs.getString(4);
               String img=rs.getString(5);
               Article p=new Article(ida, idc, titre, description, img);
     arr.add(p);
     }
    return arr;
    }
    
    public void updatetab(Article a) throws SQLException {
            try {
        PreparedStatement PS=con.prepareStatement("UPDATE `article` SET `titre`=? ,`description`=? WHERE `idarticle`=?");
        PS.setString(1,a.getTitre());
        PS.setString(2, a.getDescription());
        PS.setInt(3,a.getIda());                   
        PS.executeUpdate();
            } catch (Exception e) {
                Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE,null,e);
            }

    }
}
