/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Iservice.IService;
import Entities.Admin;
import connexion.DataBase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author NacimGastli
 */
public class ServiceAdmin {
     private Statement ste;
    private PreparedStatement pst, pst2;
    private ResultSet rs, rs2;
    private DataBase con;

    public ServiceAdmin() throws SQLException {
         con = DataBase.getInstance();
    }

    public int insert(Admin t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void Delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int update(Admin t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int SingIn(Admin p) {
         try {
             String requete = "SELECT * FROM test Where `password` =? and `role`= ? and `username` = ? ";
             
             pst = con.getConnection().prepareStatement(requete);
             pst.setString(1, p.getPassword());
             pst.setInt(2, 0);
             pst.setString(3, p.getUsername());
             
             rs = pst.executeQuery();
             
             boolean v = rs.next();
             
             if (v == true) 
                     return 3;
                 
             else 
                 return 0;
                 
             
          
         } catch (SQLException ex) {
             Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
         }
         return 0;
 }

    public int VerifierCompte(Admin t, String code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Admin chercher(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    public List<Admin> getshow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
