/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author Hamza
 */
import Entities.Article;
import IService.*;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hamza
 */
public class ServiceArticle implements IService<Article> {

    private Connection con;
    private Statement ste;
    private ResultSet res ;


    public ServiceArticle() {
    con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(Article t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `article` (`ida`, `titre`, `description`, `note`) VALUES (NULL, '"+ t.getTitre() + "', '" + t.getDescription() + "', ' + t.getNote() + ');";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(Article p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `article` ( `titre`, `description`,`note`) VALUES ( ?, ?, ?);");
    pre.setString(1, p.getTitre());
    pre.setString(2, p.getDescription());
    pre.setInt(3, p.getNote());
    pre.executeUpdate();
    }
            

    @Override
    public boolean delete(Article t) throws SQLException {
           
               PreparedStatement pre=con.prepareStatement("DELETE FROM `article` WHERE titre='"+ t.getTitre()+"' ;");
              // pre.executeUpdate();       
                int a=pre.executeUpdate();
                return(a!=0);
    }

    @Override
    public boolean update(Article t,String Description) throws SQLException {
               PreparedStatement pre=con.prepareStatement("UPDATE `article`  SET description='"+ Description  +"' where Titre='"+ t.getTitre() +"' ;");
               int a=pre.executeUpdate();
               return(a!=0);
    }

    @Override
    public List<Article> readAll() throws SQLException {
    List<Article> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from article");
     while (rs.next()) {                
               String titre=rs.getString(2);
               String description=rs.getString(3);
               int note=rs.getInt(4);
               Article p=new Article( titre ,description, note);
     arr.add(p);
     }
    return arr;
    }
    
    @Override
     public Article getById(int ida) {
          Article a = null;
         String requete = " select* from article where ida='"+ida+"'" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Article(res.getString(2),res.getString(3),res.getInt(4));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceArticle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
     
      @Override
    public List<Article> getTrier() throws SQLException {
    List<Article> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from article ORDER BY ida DESC");
     while (rs.next()) {                
               String titre=rs.getString(2);
               String description=rs.getString(3);
               int note=rs.getInt(4);
               Article p=new Article(titre, description, note);
     arr.add(p);
     }
    return arr;
    }


}
