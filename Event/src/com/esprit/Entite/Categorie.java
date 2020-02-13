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
public class Categorie {
    private int idC;
    private String nomC;
    private String lienC;

    

    public Categorie(int idC, String nomC, String lienC) {
        this.idC = idC;
        this.nomC = nomC;
        this.lienC = lienC;
        
    }

    public Categorie(String nomC, String lienC) {
        this.nomC = nomC;
        this.lienC = lienC;
        
    }

    public int getId() {
        return idC;
    }

    public void setId(int idC) {
        this.idC = idC;
    }

    public String getNom() {
        return nomC;
    }

    public void setNom(String nomC) {
        this.nomC = nomC;
    }

    public String getLien() {
        return lienC;
    }

    public void setLien(String lienC) {
        this.lienC = lienC;
    }

    

    @Override
    public String toString() {
        return "Categorie{" + "idC = " + idC + ", nomC = " + nomC + ", lienC = " + lienC + '}';
    }
    
}
