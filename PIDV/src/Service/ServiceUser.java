/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import pidv.Entite.User;
import IService.IService;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import connexion.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author azizs
 */

public class ServiceUser implements IService<User> {

    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;
    

    
    public ServiceUser() {
        con = DataBase.getInstance().getConnection();

    }

      @Override
    public void ajouter(User t) throws SQLException {
            ste = con.createStatement();
            String requeteInsert = "INSERT INTO user (`id`, `nom`, `prenom`, `login`, `mdp`, `type`) VALUES (NULL, '" + t.getNom() + "', '" + t.getPrenom() + "', '" + t.getLogin() + t.getMdp() + "', '" + t.getType() +  "');";
            ste.executeUpdate(requeteInsert);
    }
    
    public void ajouter1(User p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `baskel`.`user` ( `nom`, `prenom`, `login`, `mdp`, `type`) VALUES ( ?, ?, ?, ?, ?);");
    pre.setString(1, p.getNom());
    pre.setString(2, p.getPrenom());
    pre.setString(3, p.getLogin());
    pre.setString(4, p.getMdp());
    pre.setString(5, p.getType());
    pre.executeUpdate();
    }
            
    @Override
    public List<User> readAll() throws SQLException {
    List<User> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from user");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               String login=rs.getString(4);
               String mdp=rs.getString(5);
               String type=rs.getString(6);
               User p=new User(id, nom, prenom, login, mdp, type);
     arr.add(p);
     }
    return arr;
    }
    
    @Override
    public boolean delete(User t) throws SQLException {
      
         
       PreparedStatement pre=con.prepareStatement("DELETE FROM user WHERE nom='" + t.getNom() + "'; ");
        int a=pre.executeUpdate();
        return a!=0;
     }
     
    @Override
      public boolean update(User t,String prenom) throws SQLException {
               PreparedStatement pre=con.prepareStatement("UPDATE user  SET prenom='"+ prenom  +"' WHERE nom='" + t.getNom() +"';");
               int a=pre.executeUpdate();
               return(a!=0);
    }
   
       @Override
    public List<User> getTrier() throws SQLException {
    List<User> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet res=ste.executeQuery("select * from user ORDER BY prenom DESC");
     while (res.next()) {                
               int id=res.getInt(1);
               String nom=res.getString("nom");
               String prenom=res.getString(3);
               String login=res.getString(4);
               String mdp=res.getString(5);
               String type=res.getString(6);
               User p=new User(id, nom, prenom, login, mdp, type);
     arr.add(p);
     }
    return arr;
    }

  public User getById(int id) {
          User a = null;
         String requete = " select* from user  where id='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new User(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
  public User getByName(String n) {
          User a = null;
         String requete = " select* from user  where (nom like '"+n+"%')" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new User(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }

    @Override
    public User getById(User f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getByName(User f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
