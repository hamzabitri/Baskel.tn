/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Article;
import entities.Temoignage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataBase;

/**
 *
 * @author Omar
 */
public class TemoignageService {
     private final Connection con;
    private Statement ste;
        private ResultSet res ;


    public TemoignageService() throws SQLException {
      con = DataBase.getInstance().getCnx();

    }

     
    public void ajouter(Temoignage t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `temoignage` (`idtemoignage`, `idclient`, `titre`,`champ`, `imageT`) VALUES (NULL, ' + t.getIdclient + ' ,'" + t.getTitre()+ "','" + t.getChamp()+ "', '" + t.getImageT() + "');";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(Temoignage p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `temoignage` (`idclient`, `titre`,`champ`, `imageT`) VALUES (?,?,?,?);");
    pre.setInt(1, p.getIdc());
    pre.setString(2, p.getTitre());
    pre.setString(3, p.getChamp());
    pre.setString(4, p.getImageT());
    pre.executeUpdate();
    }
            

     
    public boolean delete(Temoignage t) throws SQLException {
           
               PreparedStatement pre=con.prepareStatement("DELETE FROM `temoignage` WHERE idtemoignage='"+ t.getIdt()+"' ;");
              // pre.executeUpdate();       
                int a=pre.executeUpdate();
                return(a!=0);
    }

     
    public boolean update(Temoignage t,String champ) throws SQLException {
               PreparedStatement pre=con.prepareStatement("UPDATE `temoignage`  SET champ='"+ champ  +"' where idclient='"+ t.getIdc()+"' ;");
               int a=pre.executeUpdate();
               return(a!=0);
    }

   
     
    public List<Temoignage> readAll() throws SQLException {
    List<Temoignage> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from temoignage");
     while (rs.next()) {  
               int idtemoignage=rs.getInt(1);
               int idclient=rs.getInt(2);
               String titre=rs.getString(3);
               String champ=rs.getString(4);
               String imageT=rs.getString(5);
               Temoignage p=new Temoignage(idtemoignage,idclient ,titre,champ, imageT);
     arr.add(p);
     }
    return arr;
   }
 
     
       
    public List<Temoignage> getTrier() throws SQLException {
    List<Temoignage> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from temoignage ORDER BY idcomment DESC");
     while (rs.next()) {                
               int idtemoignage=rs.getInt(1);
               int idclient=rs.getInt(2);
               String titre=rs.getString(3);
               String champ=rs.getString(4);
               String imageT=rs.getString(5);
               Temoignage p=new Temoignage(idtemoignage,idclient ,titre,champ, imageT);
               arr.add(p);
     }
    return arr;
       }
    
     public void updatetab(Temoignage a) throws SQLException {
            try {
        PreparedStatement PS=con.prepareStatement("UPDATE `temoignage` SET `titre`=? ,`champ`=? WHERE `idtemoignage`=?");
        PS.setString(1,a.getTitre());
        PS.setString(2, a.getChamp());
        PS.setInt(3,a.getIdt());                   
        PS.executeUpdate();
            } catch (Exception e) {
                Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE,null,e);
            }
}   
   }
