/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.User;
import java.sql.SQLException;
import java.util.List;
import Iservice.IService;
import java.sql.*;
import connexion.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author azizs
 */
public class ServiceUser implements IService<User>  {
    
    
    private Connection con;
    private Statement ste;
    private ResultSet res ;
    private PreparedStatement pst, pst2;



    public ServiceUser() {
        con = DataBase.getInstance().getConnection();

    }

     @Override
    public void ajouter(User t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `test` (`id`, `email`, `username`, `password`, `prenom`, `nom`, `cin`, `sexe`, `date_naissance`, `adresse`, `num_tel`, `photo_profil`, `role`, `desactiver`) VALUES (NULL,'" + t.getEmail() + "', '" + t.getUsername() + "', '" + t.getPassword() + "', '" + t.getPrenom() + "', '" + t.getNom() + "', '" + t.getCin() + "', '" + t.getSexe() +"', '" + t.getDate_naissance() +"', '" + t.getAdresse() +"', '" + t.getNum_tel() + "',NULL,NULL);";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(User p) throws SQLException
    {
    PreparedStatement pst=con.prepareStatement("INSERT INTO `baskel`.`test` (`id`,`email`, `username`, `password`, `prenom`, `nom` , `cin` , `sexe` , `date_naissance` , `adresse` , `num_tel` , `photo_profil`, `role`, `desactiver`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            pst.setInt(1, p.getid());
            pst.setString(2, p.getEmail());
            pst.setString(3, p.getUsername());
            pst.setString(4, p.getPassword());
            pst.setString(5, p.getPrenom());
            pst.setString(6, p.getNom());
            pst.setString(7, p.getCin());
            pst.setString(8, p.getSexe());
            pst.setDate(9, p.getDate_naissance());
            pst.setString(10, p.getAdresse());
            pst.setString(11, p.getNum_tel());
            pst.setString(12, p.getPhoto_profil());
            pst.setInt(13, 1);
            pst.setInt(14, p.getDesactiver());
            pst.executeUpdate();
    }

    

    @Override
    public boolean update(User t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public List<User> readAll() throws SQLException {
    List<User> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from personne");
     while (rs.next()) {   
               int id=rs.getInt(1);
               String email=rs.getString("email");
               String username=rs.getString("username");
               String password=rs.getString("password");
               String prenom=rs.getString("prenom");
               String nom=rs.getString("nom");
               String cin=rs.getString("cin");
               String sexe=rs.getString("sexe");
               Date date_naissance=rs.getDate("date_naissance");               
               String adresse=rs.getString("adresse");
               String num_tel=rs.getString("num_tel");
               String photo_profil=rs.getString("photo_profil");
               String status=rs.getString("status");
               int roles=rs.getInt("roles");
               int desactiver=rs.getInt("desactiver");
               User p=new User(id, email, username, password, prenom, nom, cin, sexe,date_naissance,adresse,num_tel,photo_profil,roles,desactiver);
     arr.add(p);
     }
    return arr;
    }
    
    public void delete(int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `baskel`.`test` WHERE `id`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }

    public boolean delete(User t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM event WHERE nom='" + t.getNom() + "'; ");
        int a=pre.executeUpdate();
        return a!=0;
    }
    
    public boolean update(User t,String nom) throws SQLException {
               PreparedStatement pre=con.prepareStatement("UPDATE test SET nom='"+ nom  +"' WHERE nom='" + t.getNom() +"';");
               int a=pre.executeUpdate();
               return(a!=0);
    }

 
  public void updatetab(User p) throws SQLException {
            try {
        PreparedStatement pst=con.prepareStatement("UPDATE `test` SET `email`=?,`username`=?,`prenom`=?,`nom`=?,`cin`=?,`adresse`=?,`num_tel`=? WHERE `id`=?");
            pst.setString(1, p.getEmail());
            pst.setString(2, p.getUsername());
            pst.setString(3, p.getPrenom());
            pst.setString(4, p.getNom());
            pst.setString(5, p.getCin());
            pst.setString(6, p.getAdresse());
            pst.setString(7, p.getNum_tel());
            pst.setInt(8, p.getid());
            pst.executeUpdate();
            } catch (Exception e) {
                Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE,null,e);
            }

    }
      public int SingIn(User p) {
 
        try {
             String requete = "SELECT * FROM test Where PASSWORD =? and ROLE= ? and  USERNAME = ? ";
             
             pst = con.prepareStatement(requete);
             pst.setString(1, p.getPassword());
             pst.setInt(2, 0);
             pst.setString(3, p.getUsername());
             
             res = pst.executeQuery();
             
             boolean v = res.next();
             
             if (v == true) 
                     return 3;
                 
             else 
                 return 0;
                 
             
          
         } catch (SQLException ex) {
             Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
         }
         return 0;
 }
      
      public int Singin(User p) {
 
        try {
             String requete = "SELECT * FROM test Where PASSWORD =? and ROLE= ? and  USERNAME = ? ";
             
             pst = con.prepareStatement(requete);
             pst.setString(1, p.getPassword());
             pst.setInt(2, 1);
             pst.setString(3, p.getUsername());
             
             res = pst.executeQuery();
             
             boolean v = res.next();
             
             if (v == true) 
                     return 3;
                 
             else 
                 return 0;
                 
             
          
         } catch (SQLException ex) {
             Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
         }
         return 0;
 }


}
