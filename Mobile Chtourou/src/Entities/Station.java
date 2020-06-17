/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Mariem Chtourou
 */
public class Station {
    
    private int id;
    private String nomStation;
    private double latitude;
    private double longitude;
    private int nbrVelo;
    

    public Station() { 
    }

    public Station(int id, String nomStation, double latitude, double longitude, int nbrVelo) {
        this.id = id;
        this.nomStation = nomStation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nbrVelo = nbrVelo;   
    }

    public int getId() {
        return id;
    }

    public String getNomStation() {
        return nomStation;
    }
    
    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
  
    public int getNbrVelo() {
        return nbrVelo;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setNomStation(String nomStation) {
        this.nomStation = nomStation;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public void setNbrVelo(int nbrVelo) {
        this.nbrVelo = nbrVelo;
    }
    
    @Override
    public String toString() {
        return "Station{" + "id = " + id + ", nomStation = " + nomStation +", latitude = " + latitude +", longitude = " + longitude + ", nombre de vélos = " + nbrVelo + '}';
    }
    
}
