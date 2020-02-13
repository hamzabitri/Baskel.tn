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
import Entities.Commande;
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
public class ServiceCommande implements IService<Commande> {

    private Connection con;
    private Statement ste;
        private ResultSet res ;


    public ServiceCommande() {
    con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(Commande t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `commande` (`idCommande`, `idUser`, `etat`) VALUES (NULL, ' + t.getidUser() + ', '" + t.getEtat() + "');";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(Commande p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `commande` ( `idUser`, `etat`) VALUES ( ?, ?);");
    pre.setInt(1, p.getIdUser());
    pre.setString(2, p.getEtat());
    pre.executeUpdate();
    }
            

    @Override
    public boolean delete(Commande t) throws SQLException {
           
               PreparedStatement pre=con.prepareStatement("DELETE FROM `commande` WHERE idUser='"+ t.getIdUser()+"' ;");
              // pre.executeUpdate();       
                int a=pre.executeUpdate();
                return(a!=0);
    }

    @Override
    public boolean update(Commande t,String etat) throws SQLException {
               PreparedStatement pre=con.prepareStatement("UPDATE `commande`  SET etat='"+ etat  +"' where idUser='"+ t.getIdUser() +"' ;");
               int a=pre.executeUpdate();
               return(a!=0);
    }

    @Override
    public List<Commande> readAll() throws SQLException {
    List<Commande> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from commande");
     while (rs.next()) {                
               int idCommande=rs.getInt(1);
               int idUser=rs.getInt(2);
               String etat=rs.getString(3);
               Commande p=new Commande(idCommande, idUser, etat);
     arr.add(p);
     }
    return arr;
    }
    
    @Override
     public Commande getById(int id) {
          Commande a = null;
         String requete = " select* from commande where idCommande='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Commande(res.getInt(2),res.getString(3));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
     
      @Override
    public List<Commande> getTrier() throws SQLException {
    List<Commande> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from commande ORDER BY idCommande DESC");
     while (rs.next()) {                
               int idCommande=rs.getInt(1);
               int idUser=rs.getInt(2);
               String etat=rs.getString(3);
               Commande p=new Commande(idCommande, idUser, etat);
     arr.add(p);
     }
    return arr;
    }


}
