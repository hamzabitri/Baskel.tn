/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

/**
 *
 * @author House
 */
public class Station {
    private int ids;
    private double longitude;
    private double latitude;
    private String nom_station;

    public Station(int ids, double longitude, double latitude, String nom_station) {
        this.ids = ids;
        this.longitude = longitude;
        this.latitude = latitude;
        this.nom_station = nom_station;
    }

    public Station(double longitude, double latitude, String nom_station) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.nom_station = nom_station;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public String getNomStation() {
        return nom_station;
    }

    public void setNomStation(String nom_station) {
        this.nom_station = nom_station;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Station{" + "ids=" + ids + ", longitude=" + longitude + ", latitude=" + latitude + ", nom station=" + nom_station + '}';
    }
    
}
