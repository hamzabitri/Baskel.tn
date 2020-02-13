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
import Entities.LigneCommande;
import IService2.*;
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
public class ServiceLigneCommande implements IService2<LigneCommande> {

    private Connection con;
    private Statement ste;
    private ResultSet res ;


    public ServiceLigneCommande() {
    con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(LigneCommande t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `lignecommande` (`idLC`, `idCommande`, `prix`) VALUES (NULL, ' + t.getidCommande() + ', ' + t.getPrix() + ');";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(LigneCommande p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `lignecommande` ( `idCommande`, `prix`) VALUES ( ?, ?);");
    pre.setInt(1, p.getIdCommande());
    pre.setFloat(2, p.getPrix());
    pre.executeUpdate();
    }
            

    @Override
    public boolean delete(LigneCommande t) throws SQLException {
           
               PreparedStatement pre=con.prepareStatement("DELETE FROM `lignecommande` WHERE idCommande='"+ t.getIdCommande()+"' ;");
              // pre.executeUpdate();       
                int a=pre.executeUpdate();
                return(a!=0);
    }

    @Override
    public boolean update(LigneCommande t,float prix) throws SQLException {
               PreparedStatement pre=con.prepareStatement("UPDATE `lignecommande`  SET prix='+ prix  +' where idCommande='+ t.getIdCommande() +' ;");
               int a=pre.executeUpdate();
               return(a!=0);
    }

    @Override
    public List<LigneCommande> readAll() throws SQLException {
    List<LigneCommande> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from lignecommande");
     while (rs.next()) {                
               int idLC=rs.getInt(1);
               int idCommande=rs.getInt(2);
               float prix=rs.getFloat(3);
               LigneCommande p=new LigneCommande(idLC, idCommande, prix);
     arr.add(p);
     }
    return arr;
    }
    
    @Override
     public LigneCommande getById(int id) {
          LigneCommande a = null;
         String requete = " select* from lignecommande where idLC='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new LigneCommande(res.getInt(2),res.getFloat(3));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceLigneCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
     
      @Override
    public List<LigneCommande> getTrier() throws SQLException {
    List<LigneCommande> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from lignecommande ORDER BY idLC ASC");
     while (rs.next()) {                
               int idLC=rs.getInt(1);
               int idCommande=rs.getInt(2);
               float prix=rs.getFloat(3);
               LigneCommande p=new LigneCommande(idLC, idCommande, prix);
     arr.add(p);
     }
    return arr;
    }


}
