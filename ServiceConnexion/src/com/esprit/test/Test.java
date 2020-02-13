/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.test;

import com.esprit.Entite.Station;
import com.esprit.Entite.Velo;
import com.esprit.Service.ServiceStation;
import com.esprit.Service.ServiceVelo;
import com.esprit.Utils.DataBase;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author House
 */
public class Test {
    
    public static void main(String[] args) {
        ServiceStation ser = new ServiceStation();
        
        ServiceVelo ser2 = new ServiceVelo();
        Velo v1 = new Velo("vtt",24,1);
        Station s1 = new Station(24.555,54.6,"abdou");
        Station s2 = new Station(49,62,"hamza");
        
        try {
//         
            //ser.ajouter1(s2);
            //ser2.ajouter1(v1);
            //ser.ajouter1(s1);
            //ser.delete(s1);
            //ser.update(s1);
            //List<Velo> list2=ser2.readAll();
            //List<Station> list = ser.readAll();
            List<Station> list2 = ser.getTrier();
            System.out.println(list2);
            
            Station s = ser.getById(1);            
            System.out.println(s);
            //System.out.println(list);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
