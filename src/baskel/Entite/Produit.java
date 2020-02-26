/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baskel.Entite;

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
    private float prix;

    private String image;
    private String description;
    

    public Produit(int id_produit, String nom, String reference, int id_categorie, int quantite ,float prix, String image, String description) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.reference = reference;
        this.id_categorie = id_categorie;
        this.quantite = quantite;
        this.prix = prix;
        this.image = image;
        this.description = description;
        
    }

     public Produit( String nom, String reference, int id_categorie, int quantite ,float prix, String image, String description) {
        
        this.nom = nom;
        this.reference = reference;
        this.id_categorie = id_categorie;
        this.quantite = quantite;
        this.prix = prix;
        this.image = image;
        this.description = description;
     }
        
    

    public String getImage() {
        return image;
    }
    
    
    public void setImage(String photo) {
        this.image = photo;
    }
    
    public int getId() {
        return id_produit;
    }

    public void setId(int id) {
        this. id_produit = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCategorie() {
        return id_categorie;
    }

    public void setCategorie(int cat) {
        this.id_categorie = cat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id_produit + ", nom=" + nom + ", cat=" + id_categorie + ", description=" + description + ", guantite=" + quantite + ", prix=" + prix + ", reference=" + reference + ", image=" + image + '}';
    }


    
}
