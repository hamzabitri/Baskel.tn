/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author abdel
 */
public class Produit {
     private int id_produit;
    private String nom;
    private String reference;
    private int id_categorie;
    private int quantite;
    private int prix;
    private String image;
    private String description;
    //private int etoile;

    public Produit() {
    }

    public Produit(int id_produit, String nom, String reference, int id_categorie, int quantite, int prix, String image, String description) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.reference = reference;
        this.id_categorie = id_categorie;
        this.quantite = quantite;
        this.prix = prix;
        this.image = image;
        this.description = description;
    }

    public Produit(String nom, String reference, int id_categorie, int quantite, int prix, String image, String description) {
        this.nom = nom;
        this.reference = reference;
        this.id_categorie = id_categorie;
        this.quantite = quantite;
        this.prix = prix;
        this.image = image;
        this.description = description;
    }
    
    

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

    @Override
    public String toString() {
        return "Produit{" + "id_produit=" + id_produit + ", nom=" + nom + ", reference=" + reference + ", id_categorie=" + id_categorie + ", quantite=" + quantite + ", prix=" + prix + ", image=" + image + ", description=" + description + '}';
    }

    
}
