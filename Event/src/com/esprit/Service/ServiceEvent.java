/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Categorie;
import com.esprit.Entite.Event;
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
public class ServiceEvent implements IService<Event> {

    private Connection con;
    private Statement ste;
    private ResultSet res ;

    public ServiceEvent() {
        con = DataBase.getInstance().getConnection();

    }
  
      

    @Override
    public void ajouter(Event t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO event (`id`, `nom`, `cat`, `description`, `dateDeb`, `dateFin` , `emplacement`) VALUES (NULL, '" + t.getNom() + "', '" + t.getCategorie() + "', '" + t.getDescription() + "', '" + t.getDateDeb() + "', '" + t.getDateFin() + "', '" + t.getEmplacement() + "');";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(Event p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `baskel`.`event` ( `nom`, `cat`, `description`, `dateDeb`, `dateFin`, `emplacement`) VALUES ( ?, ?, ?, ?, ?, ?);");
    pre.setString(1, p.getNom());
    pre.setString(2, p.getCategorie());
    pre.setString(3, p.getDescription());
    pre.setString(4, p.getDateDeb());
    pre.setString(5, p.getDateFin());
    pre.setString(6, p.getEmplacement());
    pre.executeUpdate();
    }
            

    @Override
    public boolean delete(Event t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM event WHERE nom='" + t.getNom() + "'; ");
        int a=pre.executeUpdate();
        return a!=0;
    }
    @Override
    public boolean update(Event t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Event> readAll() throws SQLException {
    List<Event> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from event");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String cat=rs.getString("cat");
               String description=rs.getString("description");
               String dateDeb=rs.getString("dateDeb");
               String dateFin=rs.getString("dateFin");
               String emplacement=rs.getString("emplacement");
               Event p=new Event(id, nom, cat, description, dateDeb, dateFin, emplacement);
               
     arr.add(p);
     }
    return arr;
    }
    

 public boolean update(Event t,String nom) throws SQLException {
               PreparedStatement pre=con.prepareStatement("UPDATE event  SET nom='"+ nom  +"' WHERE nom='" + t.getNom() +"';");
               int a=pre.executeUpdate();
               return(a!=0);
    }

 public Event getById(int id) {
          Event a = null;
         String requete = " select* from event  where id='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            res = ste.executeQuery(requete);
            if (res.next())
            {
                a=new Event(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
      
       public Event getByName(String nom) {
          Event a = null;
         String requete = " select* from event where (nom like '"+nom+"%')" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next()){
             a=new Event(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
    @Override
    public Event getByName(Event f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       
   @Override
    public Event getById(Event f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
