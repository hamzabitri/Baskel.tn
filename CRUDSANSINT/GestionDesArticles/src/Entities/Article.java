/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author House
 */
public class Article {
    private int ida;
    private String titre;
    private String description;
    private int note;
    

    public Article(int ida, String titre, String description, int note) {
        this.ida = ida;
        this.titre = titre;
        this.description = description;
        this.note = note;
    }

    public Article(String titre, String description, int note) {
        this.titre = titre;
        this.description = description;
        this.note = note;
    }

    public int getId() {
        return ida;
    }

    public void setId(int ida) {
        this.ida = ida;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Clubs{" + "ida = " + ida + ", titre = " + titre + ", description = " + description + ", note = " + note + '}';
    }
    
}
