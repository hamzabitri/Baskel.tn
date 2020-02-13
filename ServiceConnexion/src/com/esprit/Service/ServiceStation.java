/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Station;
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
public class ServiceStation implements IService<Station> {

    private Connection con;
    private Statement ste;

    public ServiceStation() {
        con = DataBase.getInstance().getConnection();

    }

    public void ajouter1(Station s) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `baskel.tn`.`station` ( `longitude`, `latitude`, `nom_station`) VALUES ( ?, ?, ?);");
    pre.setDouble(1, s.getLongitude());
    pre.setDouble(2, s.getLatitude());
    pre.setString(3, s.getNomStation());
    pre.executeUpdate();
    }
            
    @Override
    public void delete(Station s) throws SQLException {
	PreparedStatement pre=null;
	try {
		String query="delete from station where nom_station=?";
		pre=con.prepareStatement(query);
		pre.setString(1, s.getNomStation());
		System.out.println(pre);
		pre.executeUpdate();
	} catch (Exception e) {
		System.out.println(e);
	}

    }

    @Override
    
    public void update(Station s) throws SQLException {
        PreparedStatement per=null;
	try {
		String query="update station set longitude=?,latitude=? where nom_station=?";
		per=con.prepareStatement(query);
		per.setDouble(1, s.getLongitude());
		per.setDouble(2, s.getLatitude());
		per.setString(3, s.getNomStation());
		System.out.println(per);
		per.executeUpdate();
	} catch (Exception e) {
		System.out.println(e);
	} }

    @Override
    public List<Station> readAll() throws SQLException {
    List<Station> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from station");
     while (rs.next()) {                
               int ids=rs.getInt(1);
               double longitude=rs.getDouble("longitude");
               double latitude=rs.getDouble("latitude");
               String nom_station=rs.getString("nom_station");
               Station s=new Station(ids, longitude, latitude, nom_station);
     arr.add(s);
     }
    return arr;
    }
   
    public List<Station> getTrier() throws SQLException {
    List<Station> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from station ORDER BY nom_station ASC");
     while (rs.next()) {                
               int ids=rs.getInt(1);
               double longitude=rs.getDouble("longitude");
               double latitude=rs.getDouble("latitude");
               String nom_station=rs.getString("nom_station");
               Station p=new Station(longitude, latitude, nom_station);
     arr.add(p);
     }
    return arr;
    }
    
  public Station getById(int ids) {
          Station a = null;
         String requete = " select * from station  where IDS='"+ids+"'" ;
        try {
           
            ste = con.createStatement();
            ResultSet res=ste.executeQuery(requete);
            if (res.next())
            {a=new Station(res.getInt(1),res.getDouble(2),res.getDouble(3),res.getString(4));}
        } catch (SQLException ex) {
        }
        return a ;
        
    }
    
}
