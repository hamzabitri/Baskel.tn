/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

/**
 *
 * @author Mariem Chtourou
 */
public class Velo {
    
    private int idv;
    private String nom_velo;
    private double tarif_h;
    private int ids;

    public Velo(int idv, String nom_velo, double tarif_h, int ids) {
        this.idv=idv;
        this.nom_velo = nom_velo;
        this.tarif_h = tarif_h;
        this.ids = ids;
    }

    public Velo(String nom_velo, double tarif_h, int ids) {
        this.nom_velo = nom_velo;
        this.tarif_h = tarif_h;
        this.ids=ids;
    }

    public int getIdv() {
        return idv;
    }

    public void setIdv(int ids) {
        this.idv = idv;
    }

    public String getNomVelo() {
        return nom_velo;
    }

    public void setNomVelo(String nom_velo) {
        this.nom_velo = nom_velo;
    }
    
    public void setTarif(double tarif_h) {
        this.tarif_h = tarif_h;
    }

    public double getTarif() {
        return tarif_h;
    }

    public int getIds() {
        return ids;
    }   
   
    public void setIds(int ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "Velo{" + "idv=" + idv + ", nom du velo=" + nom_velo + ", tarif par heure=" + tarif_h + ", IDS =" + ids + '}';
    }
    
    
}
