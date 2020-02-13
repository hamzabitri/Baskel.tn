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
public class Event {
    private int id;
    private String nom;
    private String cat;
    private String description;
    private String dateDeb;
    private String dateFin;
    private String emplacement;
    

    public Event(int id, String nom, String cat, String description, String dateDeb ,String dateFin, String emplacement) {
        this.id = id;
        this.nom = nom;
        this.cat = cat;
        this.description = description;
        this.dateDeb = dateDeb;
        this.nom = nom;
        this.dateFin = dateFin;
        this.emplacement = emplacement;
    }

    public Event(String nom, String cat, String description, String dateDeb ,String dateFin, String emplacement) {
        this.nom = nom;
        this.cat = cat;
        this.description = description;
        this.dateDeb = dateDeb;
        this.nom = nom;
        this.dateFin = dateFin;
        this.emplacement = emplacement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return cat;
    }

    public void setCategorie(String cat) {
        this.cat = cat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    public String getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(String dateDeb) {
        this.dateDeb = dateDeb;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    @Override
    public String toString() {
        return "Event{" + "id = " + id + ", nom = " + nom + ", cat = " + cat + ", description = " + description + ", dateDeb = " + dateDeb + ", dateFin = " + dateFin + ", emplacement = " + emplacement + '}';
    }
    
}
