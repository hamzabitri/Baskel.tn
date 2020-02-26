/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import connexion.DataBase;
import Entities.Client;
import Entities.User;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NacimGastli
 */
public class Service_Client  {

    private Statement ste;
    private PreparedStatement pst, pst2;
    private ResultSet rs;
    private DataBase con;

    public Service_Client() throws SQLException {
        con = DataBase.getInstance();
    }

    public int Singin(Client u) {
         try {
             String requete = "SELECT * FROM test Where `password` =? and `role`= ? and `username` = ? ";
             
             pst = con.getConnection().prepareStatement(requete);
             pst.setString(1, u.getPassword());
             pst.setInt(2, 1);
             pst.setString(3, u.getUsername());
             
             rs = pst.executeQuery();
             
             boolean v = rs.next();
             
             if (v == true) 
                     return 3;
                 
             else 
                 return 0;
                 
             
          
         } catch (SQLException ex) {
             Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
         }
         return 0;
 }

//    public int insert(Client p) {
//          String md5 = getMd5(p.getPassword());
//        try {
//            String requete = "INSERT INTO fos_user(email,username,password,prenom,nom,cin,sexe,date_naissance,adresse,num_tel,roles,enabled,specialite,photo_profil) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//            pst = con.getConnection().prepareStatement(requete);
//            pst.setString(1, p.getEmail());
//            pst.setString(2, p.getUsername());
//            pst.setString(3, md5);
//            pst.setString(4, p.getPrenom());
//            pst.setString(5, p.getNom());
//            pst.setString(6, p.getCin());
//            pst.setString(7, p.getSexe());
//            pst.setDate(8, java.sql.Date.valueOf(p.getDate_naissance()));
//            pst.setString(9, p.getAdresse());
//            pst.setString(10, p.getNum_tel());
//            pst.setInt(11, 2);
//            pst.setInt(12, 0);
//            pst.setString(13, p.getSpecialite());
//            pst.setString(14, p.getPhoto_profil());
//
//            pst.executeUpdate();
//
//            return 1;
//        } catch (SQLException ex) {
//            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
//
//            return 0;
//        }
//
//    }

    public int VerifierCompte(Client p, String code) {
         
        try {
            String requete = "SELECT * FROM fos_user Where PASSWORD =? and ROLEs= ? and (EMAIL = ? OR USERNAME = ?) ";
            pst = con.getConnection().prepareStatement(requete);
            pst.setString(1, p.getPassword());
            pst.setInt(2, 2);
            pst.setString(3, p.getEmail());
            pst.setString(4, p.getUsername());

            rs = pst.executeQuery();

            boolean v = rs.next();

            if (v == true) {
                if (rs.getString("code").equals(code) == false) {
                    return 0;
                } else {
                    int id = rs.getInt("id");
                    System.out.println(id);
                    String req = "UPDATE fos_user set enabled=? where id=? ";
                    try {
                        pst2 = con.getConnection().prepareStatement(req);
                        pst2.setInt(1, 1);
                        pst2.setInt(2, id);
                        pst2.executeUpdate();
                        return 1;
                    } catch (SQLException ex) {
                        Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

//    public Client chercher(int id) {
//        try {
//            Client p = new Client();
//            String requete = "SELECT * FROM test Where id=? ";
//            pst = con.getConnection().prepareStatement(requete);
//            pst.setInt(1, id);
//            rs = pst.executeQuery();
//            while (rs.next()) {
//                Client R = new Client(rs.getInt("id"), rs.getString("email"),
//                        rs.getString("username"), rs.getString("password"), rs.getString("prenom"),
//                        rs.getString("nom"), rs.getString("adresse"), rs.getString("num_tel"), rs.getString("photo_profil"));
//                return R;
//            }
//            return p;
//        } catch (SQLException ex) {
//            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }

    public int updatetab(Client p) {
        String requete = "UPDATE test set email=?, username=?, password=?,  prenom=?, nom=?, adresse=?, num_tel=? where id = ?";
        try {
            pst = con.getConnection().prepareStatement(requete);
            pst.setString(1, p.getEmail());
            pst.setString(2, p.getUsername());
            pst.setString(3, p.getPrenom());
            pst.setString(4, p.getNom());
            pst.setString(5, p.getAdresse());
            pst.setString(6, p.getNum_tel());
            pst.setInt(7, p.getid());
            System.out.println(p);
            pst.executeUpdate();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }
    public int update(Client p) throws SQLException {
            try {
       pst = con.getConnection().prepareStatement("UPDATE `test` SET `email`=?,`username`=?,`password`=?,`prenom`=?,`nom`=?,`adresse`=?,`num_tel`=? WHERE `id`=?");
            pst.setString(1, p.getEmail());
            pst.setString(2, p.getUsername());
            pst.setString(3, p.getUsername());
            pst.setString(4, p.getPrenom());
            pst.setString(5, p.getNom());
            pst.setString(6, p.getAdresse());
            pst.setString(7, p.getNum_tel());
            pst.setInt(8, p.getid());
            pst.executeUpdate();
            return 1;
            } catch (Exception e) {
                Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE,null,e);
                return 0;
            }

    }

    //@Override
    public void Delete(int id) {
        String req = "UPDATE test set desactiver=? where id=? ";
        try {
            pst2 = con.getConnection().prepareStatement(req);
            pst2.setInt(1, 1);
            pst2.setInt(2, id);
            pst2.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    public ObservableList<Client> getshow() {
//
//        try {
//            ObservableList<Client> listService = FXCollections.observableArrayList();
//
//            String req = "SELECT * FROM fos_user where roles='2'";
//            pst = con.getConnection().prepareStatement(req);
//            rs = pst.executeQuery();
//            while (rs.next()) {
//                Client R = new Client(rs.getString("specialite"), rs.getString("etat"), rs.getInt("id"), rs.getString("email"),
//                        rs.getString("username"), rs.getString("prenom"),
//                        rs.getString("nom"), rs.getString("cin"),
//                        rs.getString("num_tel"), rs.getInt("enabled"), rs.getInt("desactiver"));
//
//                listService.add(R);
//
//            }
//            return listService;
//        } catch (SQLException ex) {
//            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//
//    }

    public int SingIn(Client t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getMd5(String input) {
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }
    public Client chercher(int id) {
        try {
            String requete = "SELECT `id`, `email`, `username`, `password`, `prenom`, `nom`, `adresse`, `num_tel`, `photo_profil`  FROM `test` WHERE `id`=? ";
            pst = con.getConnection().prepareStatement(requete);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Client R = new Client(rs.getInt("id"), rs.getString("email"),
                        rs.getString("username"), rs.getString("password"), rs.getString("prenom"),
                        rs.getString("nom"), rs.getString("adresse"), rs.getString("num_tel"), rs.getString("photo_profil"));
                System.out.println(R.getUsername());
                System.out.println(R.getAdresse());
                System.out.println(R.getPrenom());
                System.out.println(R.getNum_tel());
                return R;
                
            }
                                                    


        } catch (SQLException ex) {
            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    

}
