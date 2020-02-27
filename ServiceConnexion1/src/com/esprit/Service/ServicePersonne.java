/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Personne;
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
public class ServicePersonne implements IService<Personne> {

    private Connection con;
    private Statement ste;

    public ServicePersonne() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(Personne t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `esprit1`.`personne` (`id`, `nom`, `prenom`, `age`) VALUES (NULL, '" + t.getNom() + "', '" + t.getPrenom() + "', '" + t.getAge() + "');";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(Personne p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `esprit1`.`personne` ( `nom`, `prenom`, `age`) VALUES ( ?, ?, ?);");
    pre.setString(1, p.getNom());
    pre.setString(2, p.getPrenom());
    pre.setInt(3, p.getAge());
    pre.executeUpdate();
    }
            

    @Override
    public boolean delete(Personne t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Personne t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Personne> readAll() throws SQLException {
    List<Personne> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from personne");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String prenom=rs.getString(3);
               int age=rs.getInt("age");
               Personne p=new Personne(id, nom, prenom, age);
     arr.add(p);
     }
    return arr;
    }
}
