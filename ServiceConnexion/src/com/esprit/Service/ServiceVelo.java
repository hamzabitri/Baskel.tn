/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Velo;
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
public class ServiceVelo implements IService<Velo> {

    private Connection con;
    private Statement ste;

    public ServiceVelo() {
        con = DataBase.getInstance().getConnection();

    }

    //@Override
    //public void ajouter(Velo t) throws SQLException {
      //  ste = con.createStatement();
        //String requeteInsert = "INSERT INTO `baskel.tn`.`station` (`IDS`, `longitude`, `latitude`, `nom_station`) VALUES (NULL, '" + t.getLongitude() + "', '" + t.getLatitude() + "', '" + t.getNomStation() + "');";
        //ste.executeUpdate(requeteInsert);
    //}
    public void ajouter1(Velo v) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `baskel.tn`.`velo` ( `nom_velo`, `tarif_h`,`IDS`) VALUES ( ?, ?,?);");
    pre.setString(1, v.getNomVelo());
    pre.setDouble(2, v.getTarif());
    pre.setInt(3, v.getIds());
    pre.executeUpdate();
    }
            
    public void delete(Velo v) throws SQLException {
	PreparedStatement pre=null;
	try {
		String query="delete from velo where nom_velo=?";
		pre=con.prepareStatement(query);
		pre.setString(1, v.getNomVelo());
		System.out.println(pre);
		pre.executeUpdate();
	} catch (Exception e) {
		System.out.println(e);
	}

    }

    @Override
    
    public void update(Velo v) throws SQLException {
        PreparedStatement per=null;
	try {
		String query="update velo set nom_velo=?,tarif_h=? where nom_velo=?";
		per=con.prepareStatement(query);
		per.setString(1, v.getNomVelo());
		per.setDouble(2, v.getTarif());
		per.setString(3, v.getNomVelo());
		System.out.println(per);
		per.executeUpdate();
	} catch (Exception e) {
		System.out.println(e);
	} }

    public List<Velo> readAll() throws SQLException {
    List<Velo> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from velo");
     while (rs.next()) {                
               int idv=rs.getInt(1);
               String nom_velo=rs.getString("nom_velo");
               double tarif_h=rs.getDouble("tarif_h");
               int ids=rs.getInt(1);
               Velo v=new Velo(idv, nom_velo, tarif_h, ids);
     arr.add(v);
     }
    return arr;
    }
}
